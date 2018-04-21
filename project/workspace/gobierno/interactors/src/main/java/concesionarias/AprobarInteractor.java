package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.Aprobar;
import concesionarias.boundaries.Utils;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AprobarInteractor implements Interactor, Aprobar, Utils {


    private String generarCodigo(ConcesionariaForm form) {
        return "SUPER_CODIGO_SECRETO: " + form.getNombre() + " " + form.getConfig();
    }



    @Override
    public Function<Dao, Optional<String>> aprobarConcesionaria(ConcesionariaForm form) {
        return dao -> {
            try {
                if(exists(form).apply(dao) && form.getFechaAlta() == null && form.getCodigo() == null) {
                    String codigo = generarCodigo(form);
                    form.setCodigo(codigo);
                    form.setFechaAlta(new Date(System.currentTimeMillis()));
                    dao.update(form);
                    return Optional.of(codigo);
                }
                else return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }
    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return daoFactory -> {
            Dao dao = daoFactory.apply("Concesionarias", "concesionarias");
            Optional<String> codigo = aprobarConcesionaria((ConcesionariaForm) form).apply(dao);

            InteractorResponse response = new InteractorResponse();
            response.setResult(codigo);

            if(codigo.isPresent()) {
                response.setResponse(ResponseForward.SUCCESS);
            } else {
                response.setResponse(ResponseForward.FAILURE);
            }

            return response;
        };
    }
}
