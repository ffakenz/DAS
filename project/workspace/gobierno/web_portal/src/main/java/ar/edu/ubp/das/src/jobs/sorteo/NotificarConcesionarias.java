package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.COMPLETADO;

class NotificarConcesionarias extends SorteoStep {

    private List<Long> concesionariasQueEnviaEmail;

    public NotificarConcesionarias(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);
    }

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
