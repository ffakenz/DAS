package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo.forms.*;
import ar.edu.ubp.das.src.utils.DateUtils;
import beans.NotificationUpdate;
import clients.ConcesionariaServiceContract;
import clients.factory.IClientFactory;
import clients.responses.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class ConsumoJob {

    private static final Logger log = LoggerFactory.getLogger(ConsumoJob.class);
    private final Desnormalizer desnormalizer;

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumoJobManager consumoJobManager;
    private ClientFactoryAdapter clientFactory;

    private Timestamp fechaEjecucion;

    private static Integer FROM_DAYS = -15;
    private static Integer TO_DAYS = 0;

    // TODO: Send the DaoFactory instead of DatasourceConfig
    public ConsumoJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory) {
        this(datasourceConfig, clientFactory, Timestamp.from(Instant.now()));
    }

    public ConsumoJob(final DatasourceConfig datasourceConfig,
                      final IClientFactory clientFactory,
                      final Timestamp fechaEjecucion,
                      final Integer from,
                      final Integer to) {

        this(datasourceConfig, clientFactory, fechaEjecucion);
        FROM_DAYS = from;
        TO_DAYS = to;
    }

    public ConsumoJob(final DatasourceConfig datasourceConfig,
                      final IClientFactory clientFactory,
                      final Integer from,
                      final Integer to) {

        this(datasourceConfig, clientFactory, Timestamp.from(Instant.now()));
        FROM_DAYS = from;
        TO_DAYS = to;
    }

    public ConsumoJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory, final Timestamp fechaEjecucion) {

        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        this.concesionariasManager = new ConcesionariasManager(msConcesionariasDao);

        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);

        this.clientFactory = new ClientFactoryAdapter(clientFactory);

        this.consumoJobManager = new ConsumoJobManager(datasourceConfig);
        this.fechaEjecucion = fechaEjecucion;

        this.desnormalizer = new Desnormalizer(datasourceConfig);
    }

    public void execute() {

        log.info("STARTING_CONSUMER");

        try {
            // insert new job_consumo -> if fechaEjecucion is null the procedure bejoind createJob will set GETDATE() as default
            final JobConsumoForm jobForm = this.consumoJobManager.getMsJobConsumoDao().createJob(fechaEjecucion);
            final Long jobId = jobForm.getId();
            // tomamos todas las concesionarias aprobadas
            final List<ConcesionariaForm> concesionarias = concesionariasManager.getDao().selectAprobadas();

            // por cada concesionaria aprobada
            for (final ConcesionariaForm c : concesionarias) {

                final Long cId = c.getId();

                // obtenemos el proximo offset a usar segun su estado
                final Optional<String> lastEstadoOpt = this.consumoJobManager.getMsConsumoDao().getLastEstadoForConcesionaria(cId);

                // obtenemos sus configs
                final List<ConfigurarConcesionariaForm> configs = configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(cId);

                // usando las configs obtenemos un cliente
                final Optional<ConcesionariaServiceContract> cli = clientFactory.getClientFor(configs);
                final Timestamp from = calculateFrom(lastEstadoOpt, cId);
                final Timestamp to = DateUtils.getTimestamp(TO_DAYS);

                if (!cli.isPresent()) {
                    log.error("[error: Fail when trying to create the client][config_tecno:{}]", JsonUtils.toJsonString(configs));
                    final String description = "getClientFor(configs) failed. CONFIGS => " + configs;
                    logConsumoDb(cId, jobId, EstadoConsumo.FAILURE, from, to, null, description);
                    continue;
                }
                log.info("Client for concesionaria {} obtained with configs {}", cId, configs);

                // generamos un random rqst-id
                final String rqstId = UUID.randomUUID().toString();
                try {
                    // usamos el cliente p/ consultar planes
                    final ConcesionariaServiceContract client = cli.get(); // ifPresent was checked above
                    final List<NotificationUpdate> notificationUpdates = client.consultarPlanes(IDENTIFICADOR, from, to);
                    log.info("Consume is successfull for concesionaria {} [notification_updates:{}]", cId, JsonUtils.toJsonString(notificationUpdates));
                    final String description = String.format("consultarPlanes was success for offset [FROM:%s][TO:%s]", from, to);
                    logConsumoDb(cId, jobId, EstadoConsumo.SUCCESS, from, to, rqstId, description); // esto marca ultimo resultado como successs

                    for (final NotificationUpdate update : notificationUpdates) {
                        try {

                            final NotificationUpdateForm nuForm = transformNotificationUpdate(update, cId);
                            if (this.consumoJobManager.getMsNotificationUpdateDao().valid(nuForm)) {
                                desnormalizer.updateDb(cId, update);
                                this.consumoJobManager.getMsNotificationUpdateDao().insert(nuForm);
                                log.info("Consume Result inserted successfully for concesionaria {} [plan_id:{}][nro_cuota:{}][dni_cliente:{}]",
                                        cId, nuForm.getPlanId(), nuForm.getCuotaNroCuota(), nuForm.getClienteDocumento());
                            }
                            final String desc = "updateDb success for update: " + update;
                            logConsumoResultDb(cId, jobId, TipoConsumoResult.SUCCESS, desc);

                        } catch (final SQLException ex) {
                            log.error("Problems with update db [exception:{}]", ex.getMessage());
                            final String desc = "updateDb failed for update: " + update;
                            logConsumoResultDb(cId, jobId, TipoConsumoResult.FAILURE, desc); // esto es un fallo en el ultimmo resultado
                        }
                    }

                } catch (final ClientException ex) {
                    log.error("Problems with consultarPlanes [exception:{}]", ex.getMessage());
                    final String description = String.format("consultarPlanes failed  offset [FROM:%s][TO:%s]", from, to);
                    logConsumoDb(cId, jobId, EstadoConsumo.FAILURE, from, to, rqstId, description);
                }
            }

        } catch (final SQLException ex) {
            log.error("GOBIERNO_DB_FAILED [exception:{}]", ex.getMessage());
        }

        log.info("FINISHING_CONSUMER");
    }

    private NotificationUpdateForm transformNotificationUpdate(final NotificationUpdate nu, final Long cId) {
        final NotificationUpdateForm transformer = JsonUtils.transformer(nu, NotificationUpdateForm.class);
        transformer.setConcesionariaId(cId);
        return transformer;
    }

    /**
     * obtenemos el offset segun el estado del ultimo consumo para esa concesionaria
     *
     * @param lastEstadoOpt
     * @param concesionairaId
     * @return
     * @throws SQLException
     */
    private Timestamp calculateFrom(final Optional<String> lastEstadoOpt, final Long concesionairaId) throws SQLException {

        if (lastEstadoOpt.isPresent() && lastEstadoOpt.get().equals(EstadoConsumo.FAILURE.name())) {
            return this.consumoJobManager.getMsConsumoDao()
                            .getLastPeriodTimeForConcesionaria(concesionairaId).get();
        }
        return DateUtils.getTimestamp(FROM_DAYS);
    }

    /**
     * logueamos en la db el estado del consumo
     *
     * @param idConcesionaria
     * @param jobId
     * @param estadoConsumo
     * @param from
     * @param to
     * @param rqstId
     * @param description
     * @throws SQLException
     */
    private void logConsumoDb(final Long idConcesionaria, final Long jobId, final EstadoConsumo estadoConsumo, final Timestamp from, final Timestamp to, final String rqstId, final String description)
            throws SQLException {

        final ConsumoForm consumoForm = new ConsumoForm();
        consumoForm.setIdConcesionaria(idConcesionaria);
        consumoForm.setIdJobConsumo(jobId);
        consumoForm.setEstado(estadoConsumo.name());
        consumoForm.setFrom(from);
        consumoForm.setTo(to);
        consumoForm.setIdRequestResp(rqstId);
        consumoForm.setDescription(description);

        this.consumoJobManager.getMsConsumoDao().insert(consumoForm);
    }

    /**
     * logueamos en la db el estado del consumoResult
     *
     * @param idConcesionaria
     * @param jobId
     * @param tipoConsumoResult
     * @param description
     * @throws SQLException
     */
    private void logConsumoResultDb(final Long idConcesionaria, final Long jobId, final TipoConsumoResult tipoConsumoResult, final String description)
            throws SQLException {

        final ConsumoResultForm consumoResultForm = new ConsumoResultForm();
        consumoResultForm.setIdConcesionaria(idConcesionaria);
        consumoResultForm.setIdConsumo(jobId);
        consumoResultForm.setResult(tipoConsumoResult.name());
        consumoResultForm.setDescription(description);
        this.consumoJobManager.getMsConsumoResultDao().insert(consumoResultForm);
    }

}