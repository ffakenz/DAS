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
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RegistrarInteractor implements Registrar, Utils {

    @Override
    public Function<Dao, Optional<Long>> registrarConcesionaria(ConcesionariaForm form) {
        return dao -> {
            Boolean flag = Arrays.asList("REST", "CXF", "AXIS").contains(form.getConfig());
            if (!testConcesionariaRegistrada(form).apply(dao) && flag) {
                try {
                    dao.insert(form);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
                return getIdOf( d -> {
                    ConcesionariaForm cf = (ConcesionariaForm)d;
                    cf.setItem("id", cf.getId().toString());
                    return cf.getCuit().equals(form.getCuit());
                }).apply(dao);
            } else {
                return Optional.empty();
            }
        };
    }

    private Optional<ConcesionariaForm> makeFrom(DynaActionForm form) {
        Optional<String> nombreForm = form.getItem("nombre");
        Optional<String> config_tecnoForm = form.getItem("config");
        Optional<String> direccionForm = form.getItem("direccion");
        Optional<String> cuitForm = form.getItem("cuit");
        Optional<String> telForm = form.getItem("tel");
        Optional<String> emailForm = form.getItem("email");

        return
            nombreForm.flatMap( nombreRqst -> {
                return config_tecnoForm.flatMap( configTecnoRqst -> {
                    return direccionForm.flatMap( direccionRqst -> {
                        return cuitForm.flatMap( cuitRqst -> {
                            return telForm.flatMap( telRqst -> {
                                return emailForm.map( emailRqst -> {
                                    ConcesionariaForm concesionaria = new ConcesionariaForm();
                                    concesionaria.setNombre(nombreRqst);
                                    concesionaria.setConfig(configTecnoRqst);
                                    concesionaria.setDireccion(direccionRqst);
                                    concesionaria.setCuit(cuitRqst);
                                    concesionaria.setTel(telRqst);
                                    concesionaria.setEmail(emailRqst);
                                    return concesionaria;
                                });
                            });
                        });
                    });
                }) ;
            });
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return daoFactory -> {
            Dao dao = daoFactory.apply("Concesionarias", "concesionarias");


            Optional<ConcesionariaForm> concesinariaRqst = makeFrom(form);


            Optional<InteractorResponse> respuesta =
                concesinariaRqst.map(concesionaria -> {

                    Optional<Long> concesionariaID = registrarConcesionaria(concesionaria).apply(dao);
                    InteractorResponse response = new InteractorResponse();
                    response.setResult(concesionariaID);

                    if (concesionariaID.isPresent()) {
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
