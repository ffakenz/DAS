package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.List;

public class ConsultarAllConcesionariasInteractor implements Interactor<List<ConcesionariaForm>> {

    ConcesionariasManager concesionariasManager;

    public ConsultarAllConcesionariasInteractor(final DaoImpl concesionariasDao) {
        this.concesionariasManager = new ConcesionariasManager(concesionariasDao);
    }

    @Override
    public InteractorResponse<List<ConcesionariaForm>> execute(final DynaActionForm form) throws SQLException {

        final List<ConcesionariaForm> result = concesionariasManager.getDao().select();

        return new InteractorResponse(ResponseForward.SUCCESS, result);
    }
}