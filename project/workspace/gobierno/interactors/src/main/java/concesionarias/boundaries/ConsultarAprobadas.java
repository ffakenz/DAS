package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;

import java.util.List;
import java.util.function.Function;

public interface ConsultarAprobadas {
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas();
}
