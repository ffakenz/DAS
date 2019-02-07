package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.COMPLETADO;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_CONCESIONARIAS;

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
            // todo: agregar concesionarias_pendiente_notificacion en db para solo procesar esas
            for (final ConcesionariaForm aprobada : aprobadas) {
                try {
                    SendEmail.to(aprobada.getEmail(), "Hay un nuevo ganador !!", getEmailContent(ganador));
                } catch (SQLException | MessagingException e ) {
                    log.error("[exception:{}]", e.getMessage());
                    pendientesNotification.add(aprobada);
                }
            }
            if (!pendientesNotification.isEmpty()) {
                log.error("[exception:{}]", "Hay concesionarias pendientes de notificacion");
                // todo: guardar las fallidas para luego enviarle solo a ellas
                logSorteoFormDb(sorteoForm, PENDIENTE_NOTIFICACION_CONCESIONARIAS);
            }
        } catch (final SQLException e) {
            logSorteoFormDb(sorteoForm, PENDIENTE_NOTIFICACION_CONCESIONARIAS);
        }

        logSorteoFormDb(sorteoForm, COMPLETADO);

        return sorteoForm;
    }

    private String getEmailContent(ParticipanteForm ganador) throws SQLException {

        ConsumerForm consumerForm = sorteoJobManager.getMsConsumerDao().selectById(ganador.getIdConsumer()).get();

        StringBuilder sb = new StringBuilder();
        sb.append("<h1> El ganador del nuevo sorteo es: ");
        sb.append(consumerForm.getNombre() + " " + consumerForm.getApellido());
        sb.append("<h1>");
        sb.append("<br>");
        sb.append("<h2>");
        sb.append("Documento del ganador: ");
        sb.append(consumerForm.getDocumento());
        sb.append("</h2>");

        return sb.toString();
    }
}
