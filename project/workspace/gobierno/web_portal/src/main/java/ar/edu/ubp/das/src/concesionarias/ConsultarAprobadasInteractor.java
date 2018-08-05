package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.boundaries.ConsultarAprobadas;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConsultarAprobadasInteractor implements ConsultarAprobadas {
    @Override
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas() {
        return dao -> {
            try {
                return dao.select(null);
                        /*
                        dao.select(null).stream().filter(c -> {
                            ConcesionariaForm conc = (ConcesionariaForm) c;
                            return conc.getFechaAlta() != null && conc.getCodigo() != null;
                        }).map(c -> (ConcesionariaForm) c).collect(Collectors.toList());
                        */

            } catch (final SQLException e) {
                return new ArrayList<>();
            }
        };
    }

    @Override
    public InteractorResponse execute(final DynaActionForm form) {

        final Dao dao = new MSConcesionariasDao();
        final List<ConcesionariaForm> aprobadas = consultarAprobadas().apply(dao);
        final InteractorResponse response = new InteractorResponse();
        response.setResult(aprobadas);
        if (aprobadas.isEmpty()) {
            response.setResponse(ResponseForward.FAILURE);
        } else {
            response.setResponse(ResponseForward.SUCCESS);
        }
        return response;
    }
}
