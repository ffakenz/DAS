package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public interface Interactor {
    InteractorResponse execute(DynaActionForm form);
}
