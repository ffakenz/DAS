package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSConcPendienteNotificacionDao;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcPendienteNotificacionForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;
import clients.ConcesionariaServiceContract;
import clients.responses.ClientException;

import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_CONCESIONARIAS;

class NotificarConcesionarias extends SorteoStep {

    private MSEstadoCuentasDao msEstadoCuentasDao;
    private MSConcPendienteNotificacionDao msPendienteNotificadas;

    public NotificarConcesionarias(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);

        msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);

        this.msPendienteNotificadas = new MSConcPendienteNotificacionDao();
        msPendienteNotificadas.setDatasource(datasourceConfig);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException {

        // todo: Ganador should be an attribute in SorteoForm
        final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao().getGanadorBySorteo(sorteoForm.getId()).get();
        final EstadoCuentasForm estadoCuentasForm = msEstadoCuentasDao.selectEstadoCuentasById(ganador.getIdPlan()).get();

        if (!sorteoForm.getEstadoSorteo().equals(PENDIENTE_NOTIFICACION_CONCESIONARIAS))
            notification(sorteoForm, estadoCuentasForm);
        else {
            final List<ConcPendienteNotificacionForm> pendientesNotificacion = msPendienteNotificadas.selectBySorteoId(sorteoForm.getId());
            renotification(pendientesNotificacion, estadoCuentasForm);
        }

        if (!msPendienteNotificadas.selectBySorteoId(sorteoForm.getId()).isEmpty()) {
            final String message = String.format("[exception:%s]", "Hay concesionarias pendientes de notificacion");
            sorteoForm.setEstado(PENDIENTE_NOTIFICACION_CONCESIONARIAS);
            throw new StepRunnerException(message);
        }


        return sorteoForm;
    }


    private void renotification(final List<ConcPendienteNotificacionForm> pendientesNotificacion, final EstadoCuentasForm ganador) throws SQLException, StepRunnerException {
        for (final ConcPendienteNotificacionForm pendiente : pendientesNotificacion) {
            try {
                final ConcesionariaServiceContract client = getHttpClient(pendiente.getIdConcesionaria());
                client.notificarGanador(Constants.IDENTIFICADOR, ganador.getNroPlanConcesionaria(), ganador.getDocumentoCliente());
                msPendienteNotificadas.delete(pendiente);
            } catch (final ClientException e) {
                e.printStackTrace();
            }
        }
    }

    private void notification(final SorteoForm sorteoForm, final EstadoCuentasForm ganador) throws SQLException, StepRunnerException {

        final List<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectAprobadas();

        for (final ConcesionariaForm aprobada : aprobadas) {

            try {
                final ConcesionariaServiceContract client = getHttpClient(aprobada.getId());
                client.notificarGanador(Constants.IDENTIFICADOR, ganador.getNroPlanConcesionaria(), ganador.getDocumentoCliente());
            } catch (final ClientException e) {
                e.printStackTrace();
                final ConcPendienteNotificacionForm form = new ConcPendienteNotificacionForm();
                form.setIdConcesionaria(aprobada.getId());
                form.setIdSorteo(sorteoForm.getId());
                msPendienteNotificadas.insert(form);
            }
        }
    }
}
