package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.ConsultarAprobadas;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConsultarAprobadasInteractor implements Interactor, ConsultarAprobadas {
    @Override
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas() {
        return dao -> {
            try {
                return
                        dao.select(null).stream().filter(c -> {
                            ConcesionariaForm conc = (ConcesionariaForm)c ;
                            return conc.getFechaAlta() != null && conc.getCodigo() != null;
                        }).map( c -> (ConcesionariaForm)c).collect(Collectors.toList());
            } catch (SQLException e) {
                return new ArrayList<>();
            }
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return daoFactory ->{
            Dao dao = daoFactory.apply("Concesionarias", "concesionarias");
            List<ConcesionariaForm> aprobadas = consultarAprobadas().apply(dao);
            InteractorResponse response = new InteractorResponse();
            response.setResult(aprobadas);
            if(aprobadas.isEmpty()) {
                response.setResponse(ResponseForward.WARNING);
            } else {
                response.setResponse(ResponseForward.SUCCESS);
            }
            return response;
        };
    }
}
