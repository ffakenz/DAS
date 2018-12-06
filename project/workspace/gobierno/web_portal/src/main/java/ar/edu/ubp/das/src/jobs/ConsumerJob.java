package ar.edu.ubp.das.src.jobs;

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
import ar.edu.ubp.das.src.jobs.daos.MSParticipanteDao;
import ar.edu.ubp.das.src.jobs.daos.MSSorteoDao;
import ar.edu.ubp.das.src.jobs.sorteo.ISorteoInvariantsHolder;
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

public class ConsumerJob implements Job {

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumerManager consumerManager;
    private CuotasManager cuotasManager;
    private EstadoCuentasManager estadoCuentasManager;

    // inners
    private MSParticipanteDao msParticipanteDao;
    private MSSorteoDao msSorteoDao;

    private ClientFactoryAdapter clientFactory;
    private ISorteoInvariantsHolder sorteoInvariantsHolder;

    public ConsumerJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory) {

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

        this.msParticipanteDao = new MSParticipanteDao();
        this.msParticipanteDao.setDatasource(datasourceConfig);

        this.msSorteoDao = new MSSorteoDao();
        this.msSorteoDao.setDatasource(datasourceConfig);

        this.clientFactory = new ClientFactoryAdapter(clientFactory);
        this.sorteoInvariantsHolder = sorteoInvariantsHolder;
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        System.out.println("STARTING CONSUMER");
        try {
            /*  TODO
                String lastConsumeStatus = ....
                if(!ok) =>
                else => get last date of consumption
             */


            // tomamos todas las concesionarias aprobadas
            final List<ConcesionariaForm> concesionarias =
                    concesionariasManager.getDao().selectAprobadas();

            // por cada una
            for (final ConcesionariaForm c : concesionarias) {
                final Long cId = c.getId();
                // obtenemos sus configs
                final List<ConfigurarConcesionariaForm> configs =
                        configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(cId);
                // de las configs obtenemos un cliente
                final Optional<ConcesionariaServiceContract> cli =
                        clientFactory.getClientFor(configs);
                if (!cli.isPresent()) {
                    // todo => finish consumtion with error
                }
                final ConcesionariaServiceContract client = cli.get();
                // obtenemos el proximo offset a usar (deberia ser la fecha ??)
                final String offset = "2018-01-08T20:58:00"; // todo => get offset from DB
                // usamos el cliente p/ consultar planes // todo => validar fallo de consumo
                final List<NotificationUpdate> notificationUpdates =
                        client.consultarPlanes(offset);
                // por cada notificacion
                for (final NotificationUpdate update : notificationUpdates) {
                    // actualizamos la db
                    updateDb(c, update);
                }

            }

        } catch (final SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("FINISHING CONSUMER");
    }

    private void updateDb(final ConcesionariaForm concesionaria, final NotificationUpdate update) throws SQLException {
        // TODO REMOVE ALL UNNECESARY GETTERS AND SETTERS FROM FORMS
        updateConsumerDb(update, concesionaria.getId());
        updateEstadoCuentaDb(update, concesionaria.getId());
        updateCoutaDb(update);
    }

    private void updateConsumerDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {
        final ConsumerForm consumer = new ConsumerForm();
        final Long clienteDocumento = update.getClienteDocumento();
        consumer.setDocumento(clienteDocumento);
        if (!consumerManager.getDao().valid(consumer)) {
            final String clienteApellido = update.getClienteApellido();
            final String clienteNombre = update.getClienteNombre();
            final String clienteEmail = update.getClienteEmail();
            final String clienteNroTelefono = update.getClienteNroTelefono();
            consumer.setApellido(clienteApellido);
            consumer.setNombre(clienteNombre);
            consumer.setEmail(clienteEmail);
            consumer.setNroTelefono(clienteNroTelefono);
            consumer.setConcesionaria(concesionariaId);
            consumerManager.getDao().insert(consumer);
        }
    }

    private void updateEstadoCuentaDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {
        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        final Long planId = update.getPlanId();
        final String planEstado = update.getPlanEstado();
        final Timestamp planFechaAlta = update.getPlanFechaAlta();
        estadoCuenta.setId(planId); // ???
        estadoCuenta.setEstado(planEstado);
        estadoCuenta.setConcesionariaId(concesionariaId);
        estadoCuenta.setFechaAltaSistema(Timestamp.from(Instant.now())); // ???
        estadoCuenta.setFechaUltimaActualizacion(Timestamp.from(Instant.now()));
        estadoCuenta.setDocumentoCliente(update.getClienteDocumento());
        // estadoCuenta.setFechaAltaConcesionaria(); // ???
        // estadoCuenta.setNroPlanConcesionaria(); // ???
    }

    private void updateCoutaDb(final NotificationUpdate update) throws SQLException {
        final Long coutaNroCuota = update.getCoutaNroCuota();
        // final Timestamp cuotaFechaAlta = update.getCuotaFechaAlta(); todo => not needed
        final Timestamp cuotaFechaPago = update.getCoutaFechaPago();
        final Integer cuotaMonto = update.getCoutaMonto();
        final Timestamp cuotaFechaVencimiento = update.getCoutaFechaVencimiento();
        final CuotasForm cuota = new CuotasForm();
        cuota.setEstadoCuentaId(update.getPlanId());
        cuota.setNroCuota(coutaNroCuota);
        cuota.setFechaPago(cuotaFechaPago);
        cuota.setMonto(cuotaMonto);
        cuota.setFechaVencimiento(cuotaFechaVencimiento);
    }
}