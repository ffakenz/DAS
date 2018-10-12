package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ConsultarAprobadasInteractor implements Interactor<List<ConcesionariaForm>> {

    ConcesionariasManager concesionariasManager;

    public ConsultarAprobadasInteractor(ConcesionariasManager concesionariasManager) {
        this.concesionariasManager = concesionariasManager;
    }

    @Override
    public InteractorResponse<List<ConcesionariaForm>> execute(DynaActionForm form) throws Exception {

        final List<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectAprobadas();

        return new InteractorResponse(ResponseForward.SUCCESS, Optional.of(aprobadas));
    }
}