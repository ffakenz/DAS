package ar.edu.ubp.das.src.jobs.sorteo.model;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.ConsumoAbsoluto;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJobManager;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;
import ar.edu.ubp.das.src.utils.Utils;
import clients.ConcesionariaServiceContract;
import clients.factory.ClientFactory;
import clients.responses.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoParticipante.GANADOR;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.*;


class SorteoConstants {
    public static Integer CUOTAS_MIN = 5;
    public static Integer CUOTAS_MAX = 10;
}

class SorteoRunner {

    protected static final Logger log = LoggerFactory.getLogger(SorteoRunner.class);

    public SorteoRunner() {
    }

    private static HashMap<EstadoSorteo, String> stepsByEstado = new HashMap<EstadoSorteo, String>() {{
        put(NUEVO, RunConsumoAbsoluto.class.getSimpleName());
        put(PENDIENTE_CONSUMO, RunConsumoAbsoluto.class.getSimpleName());
        put(PENDIENTE_SELECCION_GANADOR, GetGanador.class.getSimpleName());
        put(PENDIENTE_CANCELACION, CancelarCuenta.class.getSimpleName());
        put(PENDIENTE_NOTIFICACION_GANADOR, NotificarGanador.class.getSimpleName());
        put(PENDIENTE_NOTIFICACION_CONCESIONARIAS, NotificarConcesionarias.class.getSimpleName());
    }};

    private final SorteoJobManager sorteoJobManager = new SorteoJobManager(new DatasourceConfig());

    public void ejecutarSorteo() {

        final Optional<SorteoForm> sorteoDeHoy = getSorteoDeHoy();

        final SorteoStep result = new RunConsumoAbsoluto()
                .then(new GetGanador())
                .then(new CancelarCuenta())
                .then(new NotificarGanador())
                .then(new NotificarConcesionarias());

        sorteoDeHoy.ifPresent(sorteoForm -> {

            try {
                final SorteoForm resultSorteo =
                        result.executeOnRoot(stepsByEstado.get(sorteoForm.getEstado()), sorteoForm);
                System.out.println(resultSorteo);
            } catch (final StepRunnerException e) {
                e.printStackTrace();
            }
        });

    }

    public Optional<SorteoForm> getSorteoDeHoy() {
        try {
            invalidateOldNuevosIfIsNecessary(sorteoJobManager.getMsSorteoDao().getSorteoViejosEnEstadoNuevo());

            final Optional<SorteoForm> pendiente = sorteoJobManager.getMsSorteoDao().getSorteoPendiente();
            if (pendiente.isPresent()) {
                return pendiente;
            }

            return sorteoJobManager.getMsSorteoDao().getSorteoNuevoParaHoy();

        } catch (final SQLException e) {
            log.error("[exception:{}]", e.getMessage());
            return Optional.empty();
        }
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
}

class StepRunnerException extends Exception {
    public StepRunnerException(final String message) {
        super(message);
    }
}

abstract class SorteoStep {

    protected static final Logger log = LoggerFactory.getLogger(SorteoStep.class);

    protected abstract SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException;

    protected String name;
    protected SorteoStep next;
    protected SorteoStep father;
    protected DatasourceConfig datasourceConfig;
    protected SorteoJobManager sorteoJobManager;
    protected ClientFactoryAdapter clientFactoryAdapter;
    protected ConfigurarConcesionariaManager configurarConcesionariaManager;
    protected ConcesionariasManager concesionariasManager;


    public SorteoStep() {
        this.name = this.getClass().getSimpleName();
        datasourceConfig = new DatasourceConfig();
        this.sorteoJobManager = new SorteoJobManager(datasourceConfig);
        this.clientFactoryAdapter = new ClientFactoryAdapter(ClientFactory.getInstance());
        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);
        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        concesionariasManager = new ConcesionariasManager(msConcesionariasDao);
    }

    public SorteoStep then(final SorteoStep next) {
        this.next = next;
        next.father = this;
        return next;
    }

    public SorteoForm executeOnRoot(final String stepNameToRun, final SorteoForm sorteoForm) throws StepRunnerException {
        return this.root().execute(stepNameToRun, sorteoForm);
    }

    private SorteoForm execute(final String stepNameToRun, final SorteoForm sorteoForm) throws StepRunnerException {

        if (name.equals(stepNameToRun)) {
            System.out.println("START STEP: " + name);
            final SorteoForm result = runContext(sorteoForm);
            System.out.println("FINISH STEP: " + name);
            if (next != null)
                return next.execute(next.name, result);
            return result;
        } else if (next != null) {
            return next.execute(stepNameToRun, sorteoForm);
        } else {
            return sorteoForm;
        }
    }

    private SorteoStep root() {
        if (this.father != null) {
            return this.father.root();
        } else {
            return this;
        }
    }

    protected void logSorteoFormDb(final SorteoForm sorteoForm, final EstadoSorteo estadoSorteo, final String errorMsg) throws StepRunnerException {

        log.error("[exception:{}]", errorMsg);

        sorteoForm.setEstado(estadoSorteo);
        try {
            sorteoJobManager.getMsSorteoDao().update(sorteoForm);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new StepRunnerException(name);
        }
    }

    protected void logSorteoFormDb(final SorteoForm sorteoForm, final EstadoSorteo estadoSorteo) throws StepRunnerException {

        sorteoForm.setEstado(estadoSorteo);
        try {
            sorteoJobManager.getMsSorteoDao().update(sorteoForm);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new StepRunnerException(name);
        }
    }

    protected ConcesionariaServiceContract getHttpClient(final Long concesionariaId) throws StepRunnerException {
        final Optional<ConcesionariaServiceContract> clientForConcesionaria =
                clientFactoryAdapter.getClientForConcesionaria(concesionariaId, configurarConcesionariaManager);

        if (!clientForConcesionaria.isPresent())
            throw new StepRunnerException(String.format("Get client for concesionaria %s fail", concesionariaId));

        return clientForConcesionaria.get();
    }
}

class RunConsumoAbsoluto extends SorteoStep {
    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        final ConsumoAbsoluto consumoAbsoluto =
                new ConsumoAbsoluto(datasourceConfig, clientFactoryAdapter);


        if (!consumoAbsoluto.ejecutar(sorteoForm.getId())) {
            logSorteoFormDb(sorteoForm, PENDIENTE_CONSUMO, "Consumo Absoluto Fail");
            throw new StepRunnerException(name);
        }

        logSorteoFormDb(sorteoForm, PENDIENTE_SELECCION_GANADOR);
        return sorteoForm;
    }
}

class GetGanador extends SorteoStep {

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            final List<ParticipanteForm> participantes =
                    sorteoJobManager.getMsParticipanteDao().getParticipantes(SorteoConstants.CUOTAS_MIN, SorteoConstants.CUOTAS_MAX);
            insertParticipantes(participantes, sorteoForm.getId());
            getGanador(participantes);

        } catch (final SQLException e) {
            e.printStackTrace();
            logSorteoFormDb(sorteoForm, PENDIENTE_SELECCION_GANADOR, e.getMessage());
            throw new StepRunnerException(name);
        }

        logSorteoFormDb(sorteoForm, PENDIENTE_CANCELACION);
        return sorteoForm;
    }

    private void insertParticipantes(final List<ParticipanteForm> participantes, final Long idSorteo) throws SQLException {
        for (final ParticipanteForm participante : participantes) {
            participante.setIdSorteo(idSorteo);
            sorteoJobManager.getMsParticipanteDao().insert(participante);
        }
    }

    private void getGanador(final List<ParticipanteForm> participantes) throws SQLException {
        final int indexGanador = Utils.getRandom(participantes.size());
        final ParticipanteForm ganador = participantes.get(indexGanador);
        ganador.setEstado(GANADOR);
        sorteoJobManager.getMsParticipanteDao().update(ganador);
    }
}

class CancelarCuenta extends SorteoStep {

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao()
                    .getGanadorBySorteo(sorteoForm.getId()).get();
            final ConcesionariaServiceContract client = getHttpClient(ganador.getIdConcesionaria());
            client.cancelarPlan(Constants.IDENTIFICADOR, ganador.getIdPlan());
        } catch (final ClientException | SQLException e) {
            logSorteoFormDb(sorteoForm, PENDIENTE_CANCELACION, e.getMessage());
            throw new StepRunnerException(name);
        }

        sorteoForm.setEstado(PENDIENTE_NOTIFICACION_GANADOR);
        return sorteoForm;
    }
}

class NotificarGanador extends SorteoStep {

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {
        //TODO: adding api to send email
        System.out.println("MAIL ENVIADO");
        sorteoForm.setEstado(PENDIENTE_NOTIFICACION_CONCESIONARIAS);
        return sorteoForm;
    }
}

class NotificarConcesionarias extends SorteoStep {

    private List<Long> concesionariasQueEnviaEmail;

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao().getGanadorBySorteo(sorteoForm.getId()).get();

            final List<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectAprobadas();
            final List<ConcesionariaForm> pendientesNotification = new ArrayList<>();

            for (final ConcesionariaForm aprobada : aprobadas) {

                if (enviarMail(aprobada)) {

                    System.out.println("MAIL ENVIADO A CONCESIONARIA: " + aprobada.getId());

                } else {
                    pendientesNotification.add(aprobada);
                }
            }
            if (!pendientesNotification.isEmpty()) {
                log.error("[exception:{}]", "Hay concesionarias pendientes de notificacion");
                throw new StepRunnerException("Hay concesionarias pendientes de notificacion");
            }
        } catch (final SQLException e) {

            throw new StepRunnerException("Notificar concesionarias fail");
        }

        logSorteoFormDb(sorteoForm, COMPLETADO);

        return sorteoForm;
    }

    private Boolean enviarMail(final ConcesionariaForm concesionariaForm) {
        return concesionariasQueEnviaEmail.contains(concesionariaForm.getId());
    }

    public void setConcesionariasQueEnviaEmail(final List<Long> concesionariasQueEnviaEmail) {
        this.concesionariasQueEnviaEmail = concesionariasQueEnviaEmail;
    }
}

