package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSConcPendienteNotificacionDao;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcPendienteNotificacionForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_CONCESIONARIAS;

class NotificarConcesionarias extends SorteoStep {

    private SendEmail sendEmail;
    private MSConcPendienteNotificacionDao msPendienteNotificadas;

    public NotificarConcesionarias(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter, final SendEmail sendEmail) {
        super(datasourceConfig, clientFactoryAdapter);
        this.sendEmail = sendEmail;
        this.msPendienteNotificadas = new MSConcPendienteNotificacionDao();
        msPendienteNotificadas.setDatasource(datasourceConfig);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException {
        // todo: Ganador should be an attribute in SorteoForm
        final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao().getGanadorBySorteo(sorteoForm.getId()).get();

        if (!sorteoForm.getEstadoSorteo().equals(PENDIENTE_NOTIFICACION_CONCESIONARIAS))
            notification(sorteoForm, ganador);
        else {
            final List<ConcPendienteNotificacionForm> pendientesNotificacion = msPendienteNotificadas.select(sorteoForm.getId());
            renotification(pendientesNotificacion, ganador);
        }

        if (!msPendienteNotificadas.select(sorteoForm.getId()).isEmpty()) {
            final String message = String.format("[exception:%s]", "Hay concesionarias pendientes de notificacion");
            sorteoForm.setEstado(PENDIENTE_NOTIFICACION_CONCESIONARIAS);
            throw new StepRunnerException(message);
        }

        return sorteoForm;
    }

    private void renotification(final List<ConcPendienteNotificacionForm> pendientesNotificacion, final ParticipanteForm ganador) throws SQLException {
        for (final ConcPendienteNotificacionForm pendiente : pendientesNotificacion) {
            final ConcesionariaForm concesionariaForm = concesionariasManager.getDao().selectById(pendiente.getIdConcesionaria()).get();
            try {
                sendEmail.to(concesionariaForm.getEmail(), "Hay un nuevo ganador !!", getEmailContent(ganador));
                msPendienteNotificadas.delete(pendiente);
            } catch (final MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void notification(final SorteoForm sorteoForm, final ParticipanteForm ganador) throws SQLException, StepRunnerException {
        final List<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectAprobadas();
        for (final ConcesionariaForm aprobada : aprobadas) {
            try {
                sendEmail.to(aprobada.getEmail(), "Hay un nuevo ganador !!", getEmailContent(ganador));
            } catch (final MessagingException e) {
                final ConcPendienteNotificacionForm form = new ConcPendienteNotificacionForm(sorteoForm.getId(), aprobada.getId());
                msPendienteNotificadas.insert(form);
            }
        }
    }

    private String getEmailContent(final ParticipanteForm ganador) throws SQLException {
        final ConsumerForm consumerForm = sorteoJobManager.getMsConsumerDao().selectById(ganador.getIdConsumer()).get();
        final StringBuilder sb = new StringBuilder();
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
