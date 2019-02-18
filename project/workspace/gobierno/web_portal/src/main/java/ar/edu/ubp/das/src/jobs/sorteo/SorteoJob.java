package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.DateUtils;
import clients.factory.IClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.*;

public class SorteoJob {

    protected static final Logger log = LoggerFactory.getLogger(SorteoJob.class);

    private static HashMap<EstadoSorteo, String> stepsByEstado = new HashMap<EstadoSorteo, String>() {{
        put(NUEVO, RunConsumoAbsoluto.class.getSimpleName());
        put(PENDIENTE_CONSUMO, RunConsumoAbsoluto.class.getSimpleName());
        put(PENDIENTE_CANCELACION, CancelarCuenta.class.getSimpleName());
        put(PENDIENTE_NOTIFICACION_GANADOR, NotificarGanador.class.getSimpleName());
        put(PENDIENTE_NOTIFICACION_CONCESIONARIAS, NotificarConcesionarias.class.getSimpleName());
    }};

    private final DatasourceConfig datasourceConfig;
    private final ClientFactoryAdapter clientFactoryAdapter;
    private final SorteoJobManager sorteoJobManager;
    private final SendEmail sendEmail;

    public SorteoJob(DatasourceConfig datasourceConfig, final IClientFactory iClientFactory, SendEmail sendEmail) {
        this.datasourceConfig = datasourceConfig;
        this.clientFactoryAdapter = new ClientFactoryAdapter(iClientFactory);
        this.sorteoJobManager = new SorteoJobManager(datasourceConfig);
        this.sendEmail = sendEmail;
    }

    public void execute() {

        final Optional<SorteoForm> sorteoDeHoy = getSorteoDeHoy();

        final SorteoStep result = new RunConsumoAbsoluto(datasourceConfig, clientFactoryAdapter)
                .then(new GetGanador(datasourceConfig, clientFactoryAdapter))
                .then(new CancelarCuenta(datasourceConfig, clientFactoryAdapter))
                .then(new NotificarGanador(datasourceConfig, clientFactoryAdapter, sendEmail))
                .then(new NotificarConcesionarias(datasourceConfig, clientFactoryAdapter, sendEmail));

        sorteoDeHoy.ifPresent(sorteoForm -> {

            try {
                final SorteoForm resultSorteo =
                        result.executeOnRoot(stepsByEstado.get(sorteoForm.getEstadoSorteo()), sorteoForm);
                System.out.println(resultSorteo);
            } catch (final StepRunnerException e) {
                log.error("[exception:{}]", e.getMessage());
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


    public void createSorteo() {
        SorteoForm sorteo = new SorteoForm();
        sorteo.setFechaEjecucion(DateUtils.getDayDate());
        try {
            sorteoJobManager.getMsSorteoDao().insert(sorteo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<SorteoForm> getLastSorteo()  {
        Optional<SorteoForm> lasJobCreated = Optional.empty();
        try {
            lasJobCreated = sorteoJobManager.getMsSorteoDao().select().stream().findFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lasJobCreated;
    }

}

