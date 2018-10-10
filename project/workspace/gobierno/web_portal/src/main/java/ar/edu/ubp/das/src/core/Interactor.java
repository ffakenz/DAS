package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.util.Pair;

public interface Interactor<T> {

    InteractorResponse<T> execute(DynaActionForm form) throws Exception;
}