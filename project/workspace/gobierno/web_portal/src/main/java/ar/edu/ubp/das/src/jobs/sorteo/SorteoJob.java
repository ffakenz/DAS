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
import ar.edu.ubp.das.src.utils.Utils;
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

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoParticipante.GANADOR;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CANCELACION;

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


    private void invalidateOldNuevosIfIsNecessary(final List<SorteoForm> oldNuevos) {
        oldNuevos.forEach(sorteo -> {
            try {
                sorteo.setEstado(EstadoSorteo.FALLADO.getTipo());
                sorteoJobManager.getMsSorteoDao().update(sorteo);
            } catch (final SQLException e) {
                log.error("[exception:{}]", e.getMessage());
            }
        });
    }

    public Optional<SorteoForm> getSorteoDeHoy() {
        try {

            invalidateOldNuevosIfIsNecessary(sorteoJobManager.getMsSorteoDao().getSorteoViejosEnEstadoNuevo());

            final Optional<SorteoForm> pendiente = this.sorteoJobManager.getMsSorteoDao().getSorteoPendiente();
            if (pendiente.isPresent()) {
                return pendiente;
            }

            return sorteoJobManager.getMsSorteoDao().getSorteoNuevoParaHoy();

        } catch (final SQLException e) {
            log.error("[exception:{}]", e.getMessage());
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

                    final List<ParticipanteForm> participantes = sorteoJobManager.getMsParticipanteDao().getParticipantes(1, 3);
                    insertParticipantes(participantes, sorteoForm.getId());
                    final ParticipanteForm ganador = getGanador(participantes);
                    final Long idConcesionariaGanadora = ganador.getIdConcesionaria();

                    final Optional<ConcesionariaServiceContract> clientForConcesionaria =
                            clientFactory.getClientForConcesionaria(idConcesionariaGanadora, configurarConcesionariaManager);

                    if (!clientForConcesionaria.isPresent()) {
                        sorteoForm.setEstado(PENDIENTE_CANCELACION);
                        sorteoJobManager.getMsSorteoDao().update(sorteoForm);
                        return;
                    }

                    final ConcesionariaServiceContract client = clientForConcesionaria.get();

                    try {
                        client.cancelarPlan(Constants.IDENTIFICADOR, ganador.getIdPlan());
                    } catch (final ClientException e) {
                        sorteoForm.setEstado(PENDIENTE_CANCELACION);
                        sorteoJobManager.getMsSorteoDao().update(sorteoForm);
                        return;
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

    private ParticipanteForm getGanador(final List<ParticipanteForm> participantes) {

        final int indexGanador = Utils.getRandom(participantes.size());
        final ParticipanteForm ganador = participantes.get(indexGanador);
        try {
            ganador.setEstado(GANADOR);
            sorteoJobManager.getMsParticipanteDao().update(ganador);
        } catch (final SQLException e) {
            log.error("[exception:{}]", e.getMessage());
        }
        return ganador;
    }

    private void insertParticipantes(final List<ParticipanteForm> participantes, final Long idSorteo) {
        participantes.forEach(p -> {
            try {
                p.setIdSorteo(idSorteo);
                sorteoJobManager.getMsParticipanteDao().insert(p);
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
