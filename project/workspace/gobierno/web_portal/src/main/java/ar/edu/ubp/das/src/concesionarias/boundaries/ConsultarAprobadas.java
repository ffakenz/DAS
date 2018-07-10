package ar.edu.ubp.das.src.concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.Interactor;

import java.util.List;
import java.util.function.Function;

public interface ConsultarAprobadas extends Interactor {
    public Function<Dao, List<ConcesionariaForm>> consultarAprobadas();
}
