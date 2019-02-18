package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSConcesionariasNotificadasDao;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcesionariasNotificadasForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.COMPLETADO;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_CONCESIONARIAS;

class NotificarConcesionarias extends SorteoStep {

    private SendEmail sendEmail;
    private MSConcesionariasNotificadasDao msConcNotifDao;

    public NotificarConcesionarias(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter, SendEmail sendEmail) {
        super(datasourceConfig, clientFactoryAdapter);
        this.sendEmail = sendEmail;
        this.msConcNotifDao = new MSConcesionariasNotificadasDao();
        msConcNotifDao.setDatasource(datasourceConfig);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            // todo: change table to pendientes_notificadas
            List<ConcesionariasNotificadasForm> pendientesNotificacion = msConcNotifDao.select(sorteoForm.getId());
            final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao().getGanadorBySorteo(sorteoForm.getId()).get();

            if(pendientesNotificacion.isEmpty())
                notification(sorteoForm, ganador);
            else
                renotification(pendientesNotificacion, ganador);

            existsPendientes(sorteoForm);

        } catch (final SQLException e) {
            logSorteoFormDb(sorteoForm, PENDIENTE_NOTIFICACION_CONCESIONARIAS);
            throw new StepRunnerException(e.getMessage());
        }

        logSorteoFormDb(sorteoForm, COMPLETADO);
        return sorteoForm;
    }

    private void renotification(List<ConcesionariasNotificadasForm> pendientesNotificacion, ParticipanteForm ganador)
            throws SQLException {

        for (ConcesionariasNotificadasForm pendiente : pendientesNotificacion) {

            ConcesionariaForm concesionariaForm = concesionariasManager.getDao().selectById(pendiente.getIdConcesionaria()).get();

            try {
                sendEmail.to(concesionariaForm.getEmail(), "Hay un nuevo ganador !!", getEmailContent(ganador));
                // todo: make delete procedure
                msConcNotifDao.delete(pendiente);

            } catch (SQLException | MessagingException e) {
                log.error("[exception:{}]", e.getMessage());
                continue;
            }
        }




    }

    private void notification(SorteoForm sorteoForm, ParticipanteForm ganador) throws SQLException, StepRunnerException {

        final List<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectAprobadas();
        for (final ConcesionariaForm aprobada : aprobadas) {
            try {
                sendEmail.to(aprobada.getEmail(), "Hay un nuevo ganador !!", getEmailContent(ganador));
            } catch (SQLException | MessagingException e ) {
                log.error("[exception:{}]", e.getMessage());
                ConcesionariasNotificadasForm form = new ConcesionariasNotificadasForm(sorteoForm.getId(), aprobada.getId());
                msConcNotifDao.insert(form);
            }
        }
    }

    private void existsPendientes(SorteoForm sorteoForm) throws SQLException, StepRunnerException {

        if (!msConcNotifDao.select(sorteoForm.getId()).isEmpty()) {

            logSorteoFormDb(sorteoForm, PENDIENTE_NOTIFICACION_CONCESIONARIAS);
            String message = String.format("[exception:%s]", "Hay concesionarias pendientes de notificacion");
            log.error(message);
            throw new StepRunnerException(message);
        }
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
