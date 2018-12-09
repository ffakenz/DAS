package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.Optional;

public class DesAprobarInteractor implements Interactor<Boolean> {

    private ConcesionariasManager concesionariasManager;

    public DesAprobarInteractor(final DaoImpl msConcesionariasDao) {
        this.concesionariasManager= new ConcesionariasManager(msConcesionariasDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException {

        final Pair<String, Boolean> id = form.isItemValid("id");


        if (id.snd == false)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        ConcesionariaForm concesionariaForm = form.convertTo(ConcesionariaForm.class);

        Optional<ConcesionariaForm> concesionariaToApprove = concesionariasManager.getDao().selectById(concesionariaForm);

        if(!concesionariaToApprove.isPresent())
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        concesionariasManager.getDao().disapproveConcesionaria(concesionariaForm);
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
