package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.Utils;
import concesionarias.boundaries.Registrar;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RegistrarInteractor implements Interactor, Registrar, Utils {
    @Override
    public Function<Dao, Optional<Long>> registrarConcesionaria(ConcesionariaForm form) {
        return dao -> {
            try {
                if(!exists(form).apply(dao) &&
                        form.getFechaRegistracion() == null  &&
                        form.getFechaAlta() == null) {
                    dao.insert(form);
                    return getIdOf(form).apply(dao);
                }
                return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return daoFactory -> {
            Dao dao = daoFactory.apply("Concesionaria", "concesionarias");
            Optional<Long> concesionariaID = registrarConcesionaria((ConcesionariaForm) form).apply(dao);

            InteractorResponse response = new InteractorResponse();
            response.setResult(concesionariaID);

            if(concesionariaID.isPresent()) {
                response.setResponse(ResponseForward.SUCCESS);
            } else {
                response.setResponse(ResponseForward.FAILURE);
            }

            return response;
        };
    }
}
