package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.Aprobar;
import core.UtilsCore;
import concesionarias.boundaries.Utils;
import concesionarias.forms.ConcesionariaForm;
import core.InteractorResponse;
import core.ResponseForward;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AprobarInteractor implements Aprobar, UtilsCore, Utils {


    private String generarCodigo(ConcesionariaForm form) {
        return "SUPER_CODIGO_SECRETO: " + form.getNombre() + " " + form.getConfig();
    }


    @Override
    public Function<Dao, Optional<String>> aprobarConcesionaria(ConcesionariaForm form) {
        return dao -> {
            try {

                Optional<ConcesionariaForm> concesionariaConCodigo =
                    dao.select(null)
                        .stream()
                        .map(c -> (ConcesionariaForm) c)
                        .filter( c -> {
                            return
                                testConcesionariaRegistrada(form).apply(dao) &&
                                c.getId() == form.getId() &&
                                c.getFechaAlta() == null &&
                                c.getCodigo() == null;
                        })
                        .findFirst()
                        .map( c -> {
                            String codigo = generarCodigo(c);
                            c.setCodigo(codigo);
                            c.setFechaAlta(new Date(System.currentTimeMillis()));
                            return c;
                        });

                if(concesionariaConCodigo.isPresent()) {
                    dao.update(concesionariaConCodigo.get());
                }

                return concesionariaConCodigo.map( c -> c.getCodigo());
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }




    private Optional<ConcesionariaForm> makeFrom(DynaActionForm form) {
        Optional<String> idForm = form.getItem("id");

        return idForm.map( idRqst -> {
                                ConcesionariaForm concesionaria = new ConcesionariaForm();
                                concesionaria.setId(Long.valueOf(idRqst));
                                return concesionaria;
        });
    }


    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return daoFactory -> {
            Dao dao = daoFactory.apply("Concesionarias", "concesionarias");

            Optional<ConcesionariaForm> concesionariaRqst = makeFrom(form);

            Optional<InteractorResponse> respuesta =
                concesionariaRqst.map( concesionaria -> {
                    Optional<String> codigo = aprobarConcesionaria(concesionaria).apply(dao);

                    InteractorResponse response = new InteractorResponse();
                    response.setResult(codigo);

                    if(codigo.isPresent()) {
                        response.setResponse(ResponseForward.SUCCESS);
                    } else {
                        response.setResponse(ResponseForward.FAILURE);
                    }

                    return response;
                });


            InteractorResponse response = new InteractorResponse();
            response.setResponse(ResponseForward.WARNING);
            response.setResult(Optional.empty());

            return respuesta.orElse(response); // Some error occur with the parameters



        };
    }
}
