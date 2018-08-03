package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.boundaries.Registrar;
import ar.edu.ubp.das.src.concesionarias.boundaries.Utils;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.core.UtilsCore;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class RegistrarInteractor implements Registrar, UtilsCore, Utils {

    @Override
    public Function<Dao, Optional<Long>> registrarConcesionaria(final ConcesionariaForm form) {
        return dao -> {
            final Boolean flag = Arrays.asList("REST", "CXF", "AXIS").contains(form.getConfig());
            if (!testConcesionariaRegistrada(form).apply(dao) && flag) {
                try {
                    dao.insert(form);
                } catch (final SQLException e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
                return getIdOf(d -> {
                    ConcesionariaForm cf = (ConcesionariaForm) d;
                    cf.setItem("id", cf.getId().toString());
                    return cf.getCuit().equals(form.getCuit());
                }).apply(dao);
            } else {
                return Optional.empty();
            }
        };
    }

    private Optional<ConcesionariaForm> makeFrom(final DynaActionForm form) {
        final Optional<String> nombreForm = form.getItem("nombre");
        final Optional<String> config_tecnoForm = form.getItem("config");
        final Optional<String> direccionForm = form.getItem("direccion");
        final Optional<String> cuitForm = form.getItem("cuit");
        final Optional<String> telForm = form.getItem("tel");
        final Optional<String> emailForm = form.getItem("email");

        return
                nombreForm.flatMap(nombreRqst -> {
                    return config_tecnoForm.flatMap(configTecnoRqst -> {
                        return direccionForm.flatMap(direccionRqst -> {
                            return cuitForm.flatMap(cuitRqst -> {
                                return telForm.flatMap(telRqst -> {
                                    return emailForm.map(emailRqst -> {
                                        final ConcesionariaForm concesionaria = new ConcesionariaForm();
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
                    });
                });
    }

    @Override
    public InteractorResponse execute(final DynaActionForm form) {

        Dao dao = new MSConcesionariasDao();

        final Optional<ConcesionariaForm> concesinariaRqst = makeFrom(form);

        final Optional<InteractorResponse> respuesta =
                concesinariaRqst.map(concesionaria -> {

                    final Optional<Long> concesionariaID = registrarConcesionaria(concesionaria).apply(dao);

                    final InteractorResponse response = new InteractorResponse();
                    response.setResult(concesionariaID);

                    if (concesionariaID.isPresent()) {
                        response.setResponse(ResponseForward.SUCCESS);
                    } else {
                        response.setResponse(ResponseForward.FAILURE);
                    }

                    return response;
                });


        final InteractorResponse response = new InteractorResponse();
        response.setResponse(ResponseForward.WARNING);
        response.setResult(Optional.empty());

        return respuesta.orElse(response); // Some error occur with the parameters
    }
}
