package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;
import clients.ConcesionariaServiceContract;
import clients.responses.ClientException;

import java.sql.SQLException;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CANCELACION;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_GANADOR;

class CancelarCuenta extends SorteoStep {

    MSEstadoCuentasDao msEstadoCuentasDao;

    public CancelarCuenta(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);

        msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao()
                    .getGanadorBySorteo(sorteoForm.getId()).get();
            final ConcesionariaServiceContract client = getHttpClient(ganador.getIdConcesionaria());
            EstadoCuentasForm estadoCuentasForm = msEstadoCuentasDao.selectEstadoCuentasById(ganador.getIdPlan()).get();
            client.cancelarPlan(Constants.IDENTIFICADOR, estadoCuentasForm.getNroPlanConcesionaria());
        } catch (final ClientException | SQLException e) {
            logSorteoFormDb(sorteoForm, PENDIENTE_CANCELACION, e.getMessage());
            throw new StepRunnerException(name);
        }

        sorteoForm.setEstado(PENDIENTE_NOTIFICACION_GANADOR);
        return sorteoForm;
    }
}
