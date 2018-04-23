package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;

import java.util.List;
import java.util.function.Function;

public interface ConsultarAprobadas extends Interactor {
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas();
}
