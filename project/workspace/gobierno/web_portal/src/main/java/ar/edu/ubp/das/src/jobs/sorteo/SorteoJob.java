package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.managers.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.ConsumoAbsoluto;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EjecucionesSorteoForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;
import ar.edu.ubp.das.src.utils.DateUtils;
import clients.ConcesionariaServiceContract;
import clients.factory.IClientFactory;
import clients.responses.ClientException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    private Optional<SorteoForm> getSorteoDeHoy() {
        try {
            final Optional<SorteoForm> ultimoSorteo = this.sorteoJobManager.getMsSorteoDao().getUltimoSorteo();

            if (!ultimoSorteo.isPresent() || ultimoSorteo.get().getEstadoSorteo().equals(EstadoSorteo.COMPLETADO)) {
                return sorteoJobManager.getMsSorteoDao().getSorteosByFecha(DateUtils.getDayDate());
            } else {
                return ultimoSorteo;
            }

        } catch (final SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {

        // TODO: definir estados, steps y modularizar la ejecución en distintos metodos, tener en cuenta que un sorteo pendiente_x tiene que volver a ejecutarse desde donde termino
        // TODO: revisar como resolver si falla en comunicacion a concesionarias (tener la lista en la db tal vez)

        log.info("STARTING_SORTEO");
        final Optional<SorteoForm> sorteoDeHoy = getSorteoDeHoy();

        sorteoDeHoy.ifPresent(sorteoForm -> {

            try {
                final EjecucionesSorteoForm ejecucion = new EjecucionesSorteoForm();
                final ConsumoAbsoluto consumoAbsoluto = new ConsumoAbsoluto(datasourceConfig, clientFactory);

                if (consumoAbsoluto.ejecutar(sorteoForm.getId())) {
                    final List<ParticipanteForm> participantes = sorteoJobManager.getMsParticipanteDao().getParticipantes(-5, 1, 2);
                    final int indexGanador = new Random().nextInt(participantes.size() - 1);
                    final ParticipanteForm ganador = participantes.get(indexGanador);
                    final Long idConcesionariaGanadora = ganador.getIdConcesionaria();
                    final Optional<ConcesionariaServiceContract> clientForConcesionaria =
                            clientFactory.getClientForConcesionaria(idConcesionariaGanadora, configurarConcesionariaManager);

                    if (!clientForConcesionaria.isPresent()) {
                        // mark sorteo as failed due to client for concesionaria
                        return;
                    }

                    final ConcesionariaServiceContract client = clientForConcesionaria.get();
                    try {
                        client.cancelarPlan(Constants.IDENTIFICADOR, ganador.getIdPlan()); // notificamos a la concesionaria ganadora primero
                    } catch (final ClientException e) {
                        // mark sorteo as pendiente de cancelacion
                    }

                    final List<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectAprobadas();
                    for (final ConcesionariaForm aprobada : aprobadas) {
                        final Optional<ConcesionariaServiceContract> clienteAprobada =
                                clientFactory.getClientForConcesionaria(aprobada.getId(), configurarConcesionariaManager);
                        try {
                            client.cancelarPlan(Constants.IDENTIFICADOR, ganador.getIdPlan()); // notificamos a todas las aprobadas
                        } catch (final ClientException e) {
                            // mark concesionarias sorteo as pendiente de notificacion
                        }
                    }
                    // mark sorteo as completado
                } else {
                    // mark sorteo as pendiente de consumo absoluto
                }
            } catch (final SQLException ex) {
                log.error("SORTEO [FAILED] [exception:{}]", ex.getMessage());
            }
        });
        log.info("FINISHING_CONSUMER");
    }
}
