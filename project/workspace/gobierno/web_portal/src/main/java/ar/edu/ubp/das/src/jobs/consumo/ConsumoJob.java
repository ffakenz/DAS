package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.managers.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.managers.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.managers.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo.forms.*;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import ar.edu.ubp.das.src.usuarios.managers.UsuarioManager;
import ar.edu.ubp.das.src.utils.DateUtils;
import beans.NotificationUpdate;
import clients.ConcesionariaServiceContract;
import clients.factory.IClientFactory;
import clients.responses.ClientException;
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

import static ar.edu.ubp.das.src.utils.Constants.ROL_CONSUMER;

public class ConsumoJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(ConsumoJob.class);

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumerManager consumerManager;
    private CuotasManager cuotasManager;
    private EstadoCuentasManager estadoCuentasManager;
    private ConsumoJobManager consumoJobManager;
    private UsuarioManager usuarioManager;
    private ClientFactoryAdapter clientFactory;
    private final String identificador = "GOBIERNO-INCENTIVO-2018";

    // TODO: Send the DaoFactory instead of DatasourceConfig
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

        final MSUsuariosDao msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(datasourceConfig);
        this.usuarioManager = new UsuarioManager(msUsuariosDao);

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
                final Timestamp offset = getOffset(lastEstadoOpt, cId, jobForm.getFecha()); // offset segun ultimo estado

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
                log.info("Client for concesionaria {} obtained with configs {}", cId, configs);

                // generamos un random rqst-id
                final String rqstId = UUID.randomUUID().toString();
                try {
                    // usamos el cliente p/ consultar planes
                    final ConcesionariaServiceContract client = cli.get(); // ifPresent was checked above
                    final List<NotificationUpdate> notificationUpdates = client.consultarPlanes(identificador, offset.toString());
                    log.info("Consume is successfull for concesionaria {} [notification_updates:{}]", cId, JsonUtils.toJsonString(notificationUpdates));
                    final String description = "consultarPlanes was success for offset: " + offset;
                    logConsumoDb(cId, jobId, EstadoConsumo.SUCCESS, offset, rqstId, description); // esto marca ultimo resultado como successs

                    for (final NotificationUpdate update : notificationUpdates) {
                        try {

                            final NotificationUpdateForm notificationUpdateForm = transformNotificationUpdate(update, cId);
                            if (this.consumoJobManager.getMsNotificationUpdateDao().valid(notificationUpdateForm)) {
                                updateDb(cId, update);
                                this.consumoJobManager.getMsNotificationUpdateDao().insert(notificationUpdateForm);
                            }
                            log.info("Consume Result is successfull for concesionaria {} [notification_update:{}]", cId, JsonUtils.toJsonString(notificationUpdateForm));
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
                    final String description = "consultarPlanes failed for offset: " + offset;
                    logConsumoDb(cId, jobId, EstadoConsumo.FAILURE, offset, rqstId, description);
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

    /* DESNORMALIZER */

    /**
     * @param concesionariaId
     * @param update
     * @throws SQLException
     */
    private void updateDb(final Long concesionariaId, final NotificationUpdate update) throws SQLException {
        updateConsumerDb(update);
        updateEstadoCuentaDb(update, concesionariaId);
        updateCuotaDb(update);
    }

    /**
     * @param update
     * @throws SQLException
     */
    public void updateConsumerDb(final NotificationUpdate update) throws SQLException {

        final ConsumerForm consumer = new ConsumerForm();
        consumer.setDocumento(update.getClienteDocumento());

        if (!consumerManager.getDao().valid(consumer)) {

            final UsuarioForm usuarioForm = new UsuarioForm();
            usuarioForm.setDocumento(update.getClienteDocumento());
            usuarioForm.setRol(ROL_CONSUMER);
            usuarioManager.getDao().insert(usuarioForm);

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

    /* LOGGERS */

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

}