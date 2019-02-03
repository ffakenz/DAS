package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.TipoEstadoCuenta;
import ar.edu.ubp.das.src.estado_cuentas.managers.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.ConsumoAbsoluto;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoParticipante;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import beans.PlanBean;
import clients.ConcesionariaServiceContract;
import clients.factory.IClientFactory;
import clients.responses.ClientException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static utils.MiddlewareConstants.IDENTIFICADOR;

public class SorteoJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(SorteoJob.class);

    // from web_portal
    private SorteoJobManager sorteoJobManager;
    private EstadoCuentasManager estadoCuentasManager;
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ClientFactoryAdapter clientFactory;
    private DatasourceConfig datasourceConfig;

    public SorteoJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory) {
        this.datasourceConfig = datasourceConfig;

        this.sorteoJobManager = new SorteoJobManager(datasourceConfig);
        this.clientFactory = new ClientFactoryAdapter(clientFactory);

        final MSEstadoCuentasDao msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
        estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);

        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        concesionariasManager = new ConcesionariasManager(msConcesionariasDao);

        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);

    }

    /*
    class PlanState {
        final private EstadoCuentasForm estadoCuentaGobierno;
        final private PlanBean planBeanConcesionaria;
        final private Long concesionariaId;
    }
     */


    public Boolean verificarCancelacionCuenta(final ParticipanteForm ganador) throws SQLException, ClientException {
        final Optional<ConcesionariaServiceContract> client =
                clientFactory.getClientForConcesionaria(ganador.getIdConcesionaria(), configurarConcesionariaManager);
        if (!client.isPresent()) {
            return false;
        }
        // selectEstadoCuentasById cannot fail if there is a winner
        final EstadoCuentasForm estadoCuentaGanador =
                estadoCuentasManager.getDao().selectEstadoCuentasById(ganador.getIdPlan()).get();
        final PlanBean planBeanResponse = client.get().consultarPlan(IDENTIFICADOR, ganador.getIdPlan());

        // si el plan esta cancelado en la concesionaria y pendiente de cancelacione en el sistema
        if (planBeanResponse.getPlanEstado().equals(TipoEstadoCuenta.CANCELADO.getTipo()) &&
                estadoCuentaGanador.getEstado().equals(TipoEstadoCuenta.PENDIENTE_CANCELACION.getTipo())) {

            ganador.setEstado(EstadoParticipante.GANADOR); // set participante as ganador
            estadoCuentaGanador.setEstado(TipoEstadoCuenta.CANCELADO.getTipo()); // set plan as cancelado
            sorteoJobManager.getMsParticipanteDao().update(ganador);
            estadoCuentasManager.getDao().updateEstado(estadoCuentaGanador);
            // log cancelacion de cuenta success
            return true;
        } else {
            // notify concesionaria ganador about this issue
            log.error("SORTEO [FAILED] [REASON - {}]", "ganador is not canceled");
            return false;
        }
    }


    private List<ParticipanteForm> getParticipantesSorteo(final Timestamp fechaEjecucion) throws SQLException {
        return sorteoJobManager.getMsParticipanteDao().getParticipantes(fechaEjecucion);
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        log.info("STARTING_SORTEO");
        try {
            // TODO : Verificar si hay sorteos pendientes
            // TODO : Verificar si en esta fecha hay algun sorteo nuevo
            final Optional<ParticipanteForm> ultimoGanador = sorteoJobManager.getMsParticipanteDao().getUltimoGanador();
            final Optional<ConcesionariaServiceContract> clienteUltimoGanador = ultimoGanador.flatMap(ganador ->
                    clientFactory.getClientForConcesionaria(ganador.getIdConcesionaria(), configurarConcesionariaManager)
            );
            final Timestamp fechaDelDia = Timestamp.from(Instant.now());

            final ConsumoAbsoluto consumoAbsoluto = new ConsumoAbsoluto(datasourceConfig, clientFactory);
            consumoAbsoluto.ejecutar(); // check against consumo absoluto result

            if (!ultimoGanador.isPresent()) {
                // SORTEO NUEVO
            } else if (verificarCancelacionCuenta(ultimoGanador.get())) {
                // LAST SORTEO SUCCEEDED -> set estado
                // ejecutar hasta "obtener planes"
            } else {
                // LAST SORTEO FAILED -> set estado
                // ejecutar hasta "obtener planes"
            }
        } catch (final SQLException | ClientException ex) {
            log.error("SORTEO [FAILED] [exception:{}]", ex.getMessage());
        }
        log.info("FINISHING_CONSUMER");
    }
}
