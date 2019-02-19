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

class CancelarCuenta extends SorteoStep {

    MSEstadoCuentasDao msEstadoCuentasDao;

    public CancelarCuenta(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);

        msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException {

        try {
            final ParticipanteForm ganador = sorteoJobManager.getMsParticipanteDao()
                    .getGanadorBySorteo(sorteoForm.getId()).get(); // Ganador deberia ser un atributo del sorteo
            final ConcesionariaServiceContract client = getHttpClient(ganador.getIdConcesionaria());
            final EstadoCuentasForm estadoCuentasForm = msEstadoCuentasDao.selectEstadoCuentasById(ganador.getIdPlan()).get();
            client.cancelarPlan(Constants.IDENTIFICADOR, estadoCuentasForm.getNroPlanConcesionaria());
        } catch (final ClientException e) {
            sorteoForm.setEstado(PENDIENTE_CANCELACION);
            throw new StepRunnerException(name);
        }

        return sorteoForm;
    }
}
