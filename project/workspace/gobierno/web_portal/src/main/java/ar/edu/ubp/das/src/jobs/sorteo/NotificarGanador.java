package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import javax.mail.MessagingException;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_GANADOR;

class NotificarGanador extends SorteoStep {

    SendEmail sendEmail;

    public NotificarGanador(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter, final SendEmail sendEmail) {
        super(datasourceConfig, clientFactoryAdapter);
        this.sendEmail = sendEmail;
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException {

        try {
            final ParticipanteForm ganadorBySorteo =
                    sorteoJobManager.getMsParticipanteDao().getGanadorBySorteo(sorteoForm.getId()).get(); // Ganador should be an attribute in SorteoForm

            final ConsumerForm consumerForm = sorteoJobManager.getMsConsumerDao().selectById(ganadorBySorteo.getIdConsumer()).get();
            sendEmail.to(consumerForm.getEmail(), "Felicitaciones sos el ganador !!!", getContentEmailGanador());

        } catch (final MessagingException e) {
            sorteoForm.setEstado(PENDIENTE_NOTIFICACION_GANADOR);
            throw new StepRunnerException(e.getMessage());
        }

        return sorteoForm;
    }

    private String getContentEmailGanador() {

        final StringBuilder sb = new StringBuilder();

        sb.append("<h1>SOS EL ACREDOR DE UN 0KM</h1>");
        sb.append("<h3>Por favor contactate con tu concesionaria para resolver la entrega de tu auto</h3>");

        return sb.toString();
    }
}
