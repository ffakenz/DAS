package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.boundaries.Aprobar;
import ar.edu.ubp.das.src.concesionarias.boundaries.Utils;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.core.UtilsCore;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AprobarInteractor implements Aprobar, UtilsCore, Utils {


    private String generarCodigo(final ConcesionariaForm form) {
        return "SUPER_CODIGO_SECRETO: " + form.getNombre() + " " + form.getConfig();
    }


    @Override
    public Function<Dao, Optional<String>> aprobarConcesionaria(final ConcesionariaForm form) {
        return dao -> {
            try {

                final Optional<ConcesionariaForm> concesionariaConCodigo =
                        dao.select(null)
                                .stream()
                                .map(c -> (ConcesionariaForm) c)
                                .filter(c -> {
                                    return
                                            testConcesionariaRegistrada(form).apply(dao) &&
                                                    c.getId() == form.getId() &&
                                                    c.getFechaAlta() == null &&
                                                    c.getCodigo() == null;
                                })
                                .findFirst()
                                .map(c -> {
                                    final String codigo = generarCodigo(c);
                                    c.setCodigo(codigo);
                                    c.setFechaAlta(Timestamp.from(Instant.now()));
                                    return c;
                                });

                if (concesionariaConCodigo.isPresent()) {
                    dao.update(concesionariaConCodigo.get());
                }

                return concesionariaConCodigo.map(c -> c.getCodigo());
            } catch (final SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }


    private Optional<ConcesionariaForm> makeFrom(final DynaActionForm form) {
        final Optional<String> idForm = form.getItem("id");

        return idForm.map(idRqst -> {
            final ConcesionariaForm concesionaria = new ConcesionariaForm();
            concesionaria.setId(Long.valueOf(idRqst));
            return concesionaria;
        });
    }


    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(final DynaActionForm form) {
        return daoFactory -> {
            final Dao dao = daoFactory.apply("Concesionarias", "concesionarias");

            final Optional<ConcesionariaForm> concesionariaRqst = makeFrom(form);

            final Optional<InteractorResponse> respuesta =
                    concesionariaRqst.map(concesionaria -> {
                        final Optional<String> codigo = aprobarConcesionaria(concesionaria).apply(dao);

                        final InteractorResponse response = new InteractorResponse();
                        response.setResult(codigo);

                        if (codigo.isPresent()) {
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


        };
    }
}
