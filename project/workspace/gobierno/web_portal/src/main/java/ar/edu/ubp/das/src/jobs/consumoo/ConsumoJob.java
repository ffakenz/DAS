package ar.edu.ubp.das.src.jobs.consumoo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.model.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.model.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumoo.forms.*;
import ar.edu.ubp.das.src.utils.DateUtils;
import beans.NotificationUpdate;
import clients.ConcesionariaServiceContract;
import clients.factory.IClientFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ConsumoJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(ConsumoJob.class);

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumerManager consumerManager;
    private CuotasManager cuotasManager;
    private EstadoCuentasManager estadoCuentasManager;
    private ConsumoJobManager consumoJobManager;

    private ClientFactoryAdapter clientFactory;

    public ConsumoJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory) {

        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        this.concesionariasManager = new ConcesionariasManager(msConcesionariasDao);

        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);

        final MSConsumerDao msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(datasourceConfig);
        this.consumerManager = new ConsumerManager(msConsumerDao);

        final MSCuotasDao msCuotasDao = new MSCuotasDao();
        msCuotasDao.setDatasource(datasourceConfig);
        this.cuotasManager = new CuotasManager(msCuotasDao);

        final MSEstadoCuentasDao msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
        this.estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);

        this.clientFactory = new ClientFactoryAdapter(clientFactory);

        this.consumoJobManager = new ConsumoJobManager(datasourceConfig);
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {

        log.info("STARTING_CONSUMER");

        try {
            // insert new job_consumo
            final JobConsumoForm jobForm = this.consumoJobManager.getMsJobConsumoDao().createJob();
            final Long jobId = jobForm.getId();
            // tomamos todas las concesionarias aprobadas
            final List<ConcesionariaForm> concesionarias = concesionariasManager.getDao().selectAprobadas();

            // por cada concesionaria aprobada
            for (final ConcesionariaForm c : concesionarias) {

                final Long cId = c.getId();

                // obtenemos el proximo offset a usar segun su estado
                final Optional<String> lastEstadoOpt = this.consumoJobManager.getMsConsumoDao().getLastEstadoForConcesionaria(cId);
                final Timestamp offset = getOffset(lastEstadoOpt, cId, jobForm.getFecha());

                // obtenemos sus configs
                final List<ConfigurarConcesionariaForm> configs = configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(cId);

                // usando las configs obtenemos un cliente
                final Optional<ConcesionariaServiceContract> cli = clientFactory.getClientFor(configs);

                if (!cli.isPresent()) {
                    log.error("[error: Fail when trying to create the client][config_tecno:{}]", JsonUtils.toJsonString(configs));
                    final String description = "getClientFor(configs) failed for offset " + offset + " and configs: " + configs;
                    logConsumoDb(cId, jobId, EstadoConsumo.FAILURE, offset, null, description);
                    continue;
                }

                // generamos un random rqst-id
                final String rqstId = UUID.randomUUID().toString();
                final String identificador = "GOBIERNO-INCENTIVO-2018";
                try {
                    // usamos el cliente p/ consultar planes
                    final ConcesionariaServiceContract client = cli.get(); // ifPresent was checked above
                    final List<NotificationUpdate> notificationUpdates = client.consultarPlanes(identificador, offset.toString());
                    log.info("Consume is successfull for concesionaria {} [notification_update:{}]", cId, JsonUtils.toJsonString(notificationUpdates));
                    final String description = "consultarPlanes was success for offset: " + offset;
                    logConsumoDb(cId, jobId, EstadoConsumo.SUCCESS, offset, rqstId, description);

                    for (final NotificationUpdate update : notificationUpdates) {
                        try {
                            updateDb(cId, update);
                            log.info("Consume Result is successfull for concesionaria {} [notification_update:{}]", cId, JsonUtils.toJsonString(update));
                            final String desc = "updateDb success for update: " + update;
                            logConsumoResultDb(cId, jobId, TipoConsumoResult.SUCCESS, desc);

                        } catch (final SQLException ex) {
                            log.error("Problems with update db [exception:{}]", ex.getMessage());
                            final String desc = "updateDb failed for update: " + update;
                            logConsumoResultDb(cId, jobId, TipoConsumoResult.FAILURE, desc);
                        }
                    }

                } catch (final Exception ex) {
                    log.error("Problems with consultarPlanes [exception:{}]", ex.getMessage());
                    final String description = "consultarPlanes failed for offset: " + offset;
                    logConsumoDb(cId, jobId, EstadoConsumo.FAILURE, offset, rqstId, description);
                }
            }
        } catch (final SQLException ex) {
            log.error("GOBIERNO_DB_FAILED [exception:{}]", ex.getMessage());
        }

        log.info("FINISHING_CONSUMER");
    }

    /**
     * obtenemos el offset segun el estado del ultimo consumo para esa concesionaria
     *
     * @param lastEstadoOpt
     * @param concesionairaId
     * @param fecha
     * @return
     * @throws SQLException
     */
    private Timestamp getOffset(final Optional<String> lastEstadoOpt, final Long concesionairaId, final Timestamp fecha) throws SQLException {
        if (lastEstadoOpt.isPresent() && lastEstadoOpt.get().equals(EstadoConsumo.FAILURE.name())) {
            return this.consumoJobManager.getMsConsumoDao().getLastOffsetForConcesionaria(concesionairaId).get();
        }
        return DateUtils.getTimestampFrom(fecha, -15);
    }

    /**
     * logueamos en la db el estado del consumo
     *
     * @param idConcesionaria
     * @param jobId
     * @param estadoConsumo
     * @param offset
     * @param rqstId
     * @param description
     * @throws SQLException
     */
    private void logConsumoDb(final Long idConcesionaria, final Long jobId, final EstadoConsumo estadoConsumo, final Timestamp offset, final String rqstId, final String description)
            throws SQLException {

        final ConsumoForm consumoForm = new ConsumoForm();
        consumoForm.setIdConcesionaria(idConcesionaria);
        consumoForm.setIdJobConsumo(jobId);
        consumoForm.setEstado(estadoConsumo.name());
        consumoForm.setOffset(offset);
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


    /**
     * @param concesionariaId
     * @param update
     * @throws SQLException
     */
    private void updateDb(final Long concesionariaId, final NotificationUpdate update) throws SQLException {
        updateConsumerDb(update, concesionariaId);
        updateEstadoCuentaDb(update, concesionariaId);
        updateCuotaDb(update);
    }

    /**
     * @param update
     * @param concesionariaId
     * @throws SQLException
     */
    public void updateConsumerDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {

        final ConsumerForm consumer = new ConsumerForm();
        consumer.setDocumento(update.getClienteDocumento());
        if (!consumerManager.getDao().valid(consumer)) {
            consumer.setNombre(update.getClienteNombre());
            consumer.setApellido(update.getClienteApellido());
            consumer.setNroTelefono(update.getClienteNroTelefono());
            consumer.setEmail(update.getClienteEmail());
            consumerManager.getDao().insert(consumer);
        }
    }

    /**
     * @param update
     * @param concesionariaId
     * @throws SQLException
     */
    public void updateEstadoCuentaDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {

        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        estadoCuenta.setNroPlanConcesionaria(update.getPlanId());
        estadoCuenta.setDocumentoCliente(update.getClienteDocumento());
        estadoCuenta.setVehiculo(update.getVehiculoId());
        estadoCuenta.setFechaAltaConcesionaria(update.getPlanFechaAlta());
        estadoCuenta.setEstado(update.getPlanEstado());
        estadoCuenta.setConcesionariaId(concesionariaId);
        estadoCuentasManager.getDao().upsert(estadoCuenta);
    }

    /**
     * @param update
     * @throws SQLException
     */
    public void updateCuotaDb(final NotificationUpdate update) throws SQLException {

        final CuotasForm cuota = new CuotasForm();
        cuota.setEstadoCuentaId(update.getPlanId());
        cuota.setNroCuota(update.getCuotaNroCuota());
        cuota.setFechaVencimiento(update.getCuotaFechaVencimiento());
        cuota.setMonto(update.getCuotaMonto());
        cuota.setFechaPago(update.getCuotaFechaPago());
        cuota.setFechaAltaConcesionaria(update.getCuotaFechaAlta());
        cuotasManager.getDao().upsert(cuota);
    }
}