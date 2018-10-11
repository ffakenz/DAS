package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class AprobarInteractor implements Interactor<Boolean> {


    private String generarCodigo(final ConcesionariaForm form) {
        return "SUPER_CODIGO_SECRETO_" + form.getId();
    }


    private ConcesionariasManager concesionariasManager;

    public AprobarInteractor(final MSConcesionariasDao msConcesionariasDao) {
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

        concesionariaForm.setCodigo(generarCodigo(concesionariaForm));
        concesionariasManager.getDao().approveConcesionaria(concesionariaForm);
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
