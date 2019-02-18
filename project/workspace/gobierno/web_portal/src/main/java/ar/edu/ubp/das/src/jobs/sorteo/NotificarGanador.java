package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Optional;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_CONCESIONARIAS;

class NotificarGanador extends SorteoStep {

    SendEmail sendEmail;

    public NotificarGanador(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter, SendEmail sendEmail) {
        super(datasourceConfig, clientFactoryAdapter);
        this.sendEmail = sendEmail;
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            Optional<ParticipanteForm> ganadorBySorteo =
                    sorteoJobManager.getMsParticipanteDao().getGanadorBySorteo(sorteoForm.getId());

            if(ganadorBySorteo.isPresent()) {
                ConsumerForm consumerForm = sorteoJobManager.getMsConsumerDao().selectById(ganadorBySorteo.get().getIdConsumer()).get();

                sendEmail.to(consumerForm.getEmail(), "Felicitaciones sos el ganador !!!", getContentEmailGanador());
            }

        } catch (SQLException | MessagingException  e) {
            log.error("[exception:{}]", e.getMessage());
            logSorteoFormDb(sorteoForm, PENDIENTE_NOTIFICACION_CONCESIONARIAS, e.getMessage());
        }

        sorteoForm.setEstado(PENDIENTE_NOTIFICACION_CONCESIONARIAS);
        return sorteoForm;
    }

    private String getContentEmailGanador() {

        StringBuilder sb = new StringBuilder();

        sb.append("<h1>SOS EL ACREDOR DE UN 0KM</h1>");
        sb.append("<h3>Por favor contactate con tu concesionaria para resolver la entrega de tu auto</h3>");

        return sb.toString();
    }
}
