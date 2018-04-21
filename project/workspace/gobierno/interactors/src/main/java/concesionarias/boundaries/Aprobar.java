package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;

import java.util.Optional;
import java.util.function.Function;

public interface Aprobar {
    Function<Dao, Optional<String>> aprobarConcesionaria(ConcesionariaForm form);
}
