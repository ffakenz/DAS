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
import ar.edu.ubp.das.src.jobs.consumoo.forms.ConsumoForm;
import ar.edu.ubp.das.src.jobs.consumoo.forms.ConsumoResultForm;
import ar.edu.ubp.das.src.jobs.consumoo.forms.EstadoConsumo;
import ar.edu.ubp.das.src.jobs.consumoo.forms.TipoConsumoResult;
import beans.NotificationUpdate;
import clients.ConcesionariaServiceContract;
import clients.IClientFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ConsumoJob implements Job {

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
        System.out.println("STARTING_CONSUMER");
        try {
            // insert new job_consumo
            final Long jobId = this.consumoJobManager.getMsJobConsumoDao().createJob();
            // tomamos todas las concesionarias aprobadas
            final List<ConcesionariaForm> concesionarias = concesionariasManager.getDao().selectAprobadas();

            // por cada concesionaria aprobada
            for (final ConcesionariaForm c : concesionarias) {
                final Long cId = c.getId();

                // obtenemos el proximo offset a usar segun su estado
                final Optional<String> lastEstadoOpt = this.consumoJobManager.getMsConsumoDao().getLastEstadoForConcesionaria(cId);
                final Timestamp offset;
                if (!lastEstadoOpt.isPresent()) {
                    // is a new consumo => offset should be the last date possible
                    offset = Timestamp.valueOf("2018-01-08T20:58:00"); // Beggining of time
                } else {
                    final String lastEstado = lastEstadoOpt.get();
                    if (lastEstado.equals(EstadoConsumo.FAILURE.name())) {
                        offset = this.consumoJobManager.getMsConsumoDao().getLastOffsetForConcesionaria(cId).get(); // this should not fail as estado is present
                    } else {
                        offset = Timestamp.from(Instant.now());
                    }
                }

                // obtenemos sus configs
                final List<ConfigurarConcesionariaForm> configs = configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(cId);
                // usando las configs obtenemos un cliente
                final Optional<ConcesionariaServiceContract> cli = clientFactory.getClientFor(configs);
                if (!cli.isPresent()) {
                    // loggeamos Consumo Failure (RqstId == NULL)
                    final ConsumoForm consumoForm = new ConsumoForm();
                    consumoForm.setIdConcesionaria(cId);
                    consumoForm.setIdJobConsumo(jobId);
                    consumoForm.setEstado(EstadoConsumo.FAILURE.name());
                    consumoForm.setOffset(offset);
                    consumoForm.setDescription("getClientFor(configs) failed for offset " + offset + " and configs: " + configs);
                    this.consumoJobManager.getMsConsumoDao().insert(consumoForm);
                } else {
                    // generamos un random rqst-id
                    final String rqstId = UUID.randomUUID().toString();
                    try {
                        // usamos el cliente p/ consultar planes
                        final ConcesionariaServiceContract client = cli.get();
                        final List<NotificationUpdate> notificationUpdates = client.consultarPlanes(offset.toString());
                        // loggeamos Consumo Success
                        final ConsumoForm consumoForm = new ConsumoForm();
                        consumoForm.setIdConcesionaria(cId);
                        consumoForm.setIdJobConsumo(jobId);
                        consumoForm.setEstado(EstadoConsumo.SUCCESS.name());
                        consumoForm.setOffset(offset);
                        consumoForm.setIdRequestResp(rqstId);
                        consumoForm.setDescription("consultarPlanes was success for offset: " + offset);
                        this.consumoJobManager.getMsConsumoDao().insert(consumoForm);
                        // por cada notificacion
                        for (final NotificationUpdate update : notificationUpdates) {
                            try {
                                // actualizamos la db
                                updateDb(cId, update);
                                // loggeamos Consumo Result Success
                                final ConsumoResultForm consumoResultForm = new ConsumoResultForm();
                                consumoResultForm.setIdConcesionaria(cId);
                                consumoResultForm.setIdConsumo(jobId);
                                consumoResultForm.setResult(TipoConsumoResult.SUCCESS.name());
                                consumoResultForm.setDescription("updateDb success for update: " + update);
                                this.consumoJobManager.getMsConsumoResultDao().insert(consumoResultForm);
                            } catch (final SQLException ex) {
                                ex.printStackTrace();
                                // loggeamos Consumo Result Failure
                                final ConsumoResultForm consumoResultForm = new ConsumoResultForm();
                                consumoResultForm.setIdConcesionaria(cId);
                                consumoResultForm.setIdConsumo(jobId);
                                consumoResultForm.setResult(TipoConsumoResult.FAILURE.name());
                                consumoResultForm.setDescription("updateDb failed for update: " + update);
                                this.consumoJobManager.getMsConsumoResultDao().insert(consumoResultForm);
                            }
                        }
                    } catch (final Exception ex) {
                        ex.printStackTrace();
                        // loggeamos Consumo Failure
                        final ConsumoForm consumoForm = new ConsumoForm();
                        consumoForm.setIdConcesionaria(cId);
                        consumoForm.setIdJobConsumo(jobId);
                        consumoForm.setEstado(EstadoConsumo.FAILURE.name());
                        consumoForm.setOffset(offset);
                        consumoForm.setIdRequestResp(rqstId);
                        consumoForm.setDescription("consultarPlanes failed for offset: " + offset);
                        this.consumoJobManager.getMsConsumoDao().insert(consumoForm);
                    }
                }
            }
        } catch (final SQLException ex) {
            ex.printStackTrace();
            System.out.println("GOBIERNO_DB_FAILED");
        }
        System.out.println("FINISHING_CONSUMER");
    }

    // TODO move all this methods to a differnt object named DesNormalizer
    private void updateDb(final Long concesionariaId, final NotificationUpdate update) throws SQLException {
        updateConsumerDb(update, concesionariaId);
        updateEstadoCuentaDb(update, concesionariaId);
        updateCuotaDb(update);
    }

    public void updateConsumerDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {
        final ConsumerForm consumer = new ConsumerForm();
        final Long clienteDocumento = update.getClienteDocumento();
        consumer.setDocumento(clienteDocumento);
        if (!consumerManager.getDao().valid(consumer)) {
            final String clienteNombre = update.getClienteNombre();
            final String clienteApellido = update.getClienteApellido();
            final String clienteNroTelefono = update.getClienteNroTelefono();
            final String clienteEmail = update.getClienteEmail();
            consumer.setNombre(clienteNombre);
            consumer.setApellido(clienteApellido);
            consumer.setNroTelefono(clienteNroTelefono);
            consumer.setEmail(clienteEmail);
            consumer.setConcesionaria(concesionariaId);
            consumerManager.getDao().insert(consumer);
        }
    }

    public void updateEstadoCuentaDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {
        final Long planId = update.getPlanId();
        final Long clienteDocumento = update.getClienteDocumento();
        final Long vehiculo = update.getVehiculoId();
        final Timestamp planFechaAlta = update.getPlanFechaAlta();
        final String planEstado = update.getPlanEstado();
        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        estadoCuenta.setNroPlanConcesionaria(planId);
        estadoCuenta.setDocumentoCliente(clienteDocumento);
        estadoCuenta.setVehiculo(vehiculo);
        estadoCuenta.setFechaAltaConcesionaria(planFechaAlta);
        estadoCuenta.setEstado(planEstado);
        estadoCuenta.setConcesionariaId(concesionariaId);
        estadoCuentasManager.getDao().upsert(estadoCuenta);
    }

    public void updateCuotaDb(final NotificationUpdate update) throws SQLException {
        final Long planId = update.getPlanId();
        final Long cuotaNroCuota = update.getCoutaNroCuota();
        final Timestamp cuotaFechaVencimiento = update.getCoutaFechaVencimiento();
        final Integer cuotaMonto = update.getCoutaMonto();
        final Timestamp cuotaFechaPago = update.getCoutaFechaPago();
        final Timestamp cuotaFechaAlta = update.getCuotaFechaAlta();
        final CuotasForm cuota = new CuotasForm();
        cuota.setEstadoCuentaId(planId);
        cuota.setNroCuota(cuotaNroCuota);
        cuota.setFechaVencimiento(cuotaFechaVencimiento);
        cuota.setMonto(cuotaMonto);
        cuota.setFechaPago(cuotaFechaPago);
        cuota.setFechaAltaConcesionaria(cuotaFechaAlta);
        cuotasManager.getDao().upsert(cuota);
    }
}