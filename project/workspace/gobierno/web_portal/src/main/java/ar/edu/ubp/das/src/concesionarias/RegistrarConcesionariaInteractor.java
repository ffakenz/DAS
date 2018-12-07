package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.Arrays;

public class RegistrarConcesionariaInteractor implements Interactor<Boolean> {

    private ConcesionariasManager concesionariasManager;

    public RegistrarConcesionariaInteractor(final DaoImpl msConcesionariasDao) {
        this.concesionariasManager= new ConcesionariasManager(msConcesionariasDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) {

        System.out.println("entro al registrar interactor");

        final Pair<String, Boolean> nombre = form.isItemValid("nombre");
        final Pair<String, Boolean> direccion = form.isItemValid("direccion");
        final Pair<String, Boolean> cuit = form.isItemValid("cuit");
        final Pair<String, Boolean> tel = form.isItemValid("tel");
        final Pair<String, Boolean> email = form.isItemValid("email");


        final Boolean someIsMissing = Arrays.asList(nombre, direccion, cuit, tel, email)
                .stream().anyMatch(v -> v.snd == false);

        // Some error occur with the input values
        if (someIsMissing)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        try {
            concesionariasManager.getDao().insert(form.convertTo(ConcesionariaForm.class));
        } catch (SQLException e) {
            return new InteractorResponse<>(ResponseForward.FAILURE, false);
        }
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
