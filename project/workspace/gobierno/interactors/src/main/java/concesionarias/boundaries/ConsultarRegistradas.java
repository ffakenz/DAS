package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.ConcesionariaForm;
import core.Interactor;

import java.util.List;
import java.util.function.Function;

// TODO
public interface ConsultarRegistradas extends Interactor {
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas();
}
