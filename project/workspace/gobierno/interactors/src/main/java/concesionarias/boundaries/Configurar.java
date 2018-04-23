package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;
import concesionarias.forms.ConfigParamForm;
import core.Interactor;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Configurar extends Interactor {
    public Function<BiFunction<String, String, Dao>, Boolean> configurarConcesionaria(ConfigParamForm configParam);
}
