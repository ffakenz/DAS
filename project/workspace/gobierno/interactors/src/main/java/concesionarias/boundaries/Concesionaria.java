package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface Concesionaria {
    Function<Dao, Optional<Long>> registrarConcesionaria(ConcesionariaForm form);
    Function<Dao, Optional<String>> aprobarConcesionaria(ConcesionariaForm form);
    Function<Dao, List<ConcesionariaForm>> consultarAprobadas();
    Function<Dao, Optional<Long>> configurarConcesionaria(ConcesionariaForm form);
}
