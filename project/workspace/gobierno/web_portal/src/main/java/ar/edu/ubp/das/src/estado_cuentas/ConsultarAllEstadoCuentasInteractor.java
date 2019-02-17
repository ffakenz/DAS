package ar.edu.ubp.das.src.estado_cuentas;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.managers.EstadoCuentasManager;

import java.sql.SQLException;
import java.util.List;

public class ConsultarAllEstadoCuentasInteractor implements Interactor<List<EstadoCuentasForm>> {

    EstadoCuentasManager estadoCuentasManager;

    public ConsultarAllEstadoCuentasInteractor(final DaoImpl msEstadoCuentasDao) {
        this.estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);
    }

    @Override
    public InteractorResponse<List<EstadoCuentasForm>> execute(final DynaActionForm form) throws SQLException {

        final List<EstadoCuentasForm> result = estadoCuentasManager.getDao().select();

        return new InteractorResponse(ResponseForward.SUCCESS, result);
    }
}