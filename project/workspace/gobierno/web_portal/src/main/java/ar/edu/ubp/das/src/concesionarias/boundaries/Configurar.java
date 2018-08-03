package ar.edu.ubp.das.src.concesionarias.boundaries;

import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;
import ar.edu.ubp.das.src.core.Interactor;

public interface Configurar extends Interactor {
    public Boolean configurarConcesionaria(ConfigParamForm configParam);
}
