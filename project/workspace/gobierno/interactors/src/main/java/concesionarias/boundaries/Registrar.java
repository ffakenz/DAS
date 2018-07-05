package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.ConcesionariaForm;
import core.Interactor;

import java.util.Optional;
import java.util.function.Function;

public interface Registrar extends Interactor {
    Function<Dao, Optional<Long>> registrarConcesionaria(ConcesionariaForm form);
}
