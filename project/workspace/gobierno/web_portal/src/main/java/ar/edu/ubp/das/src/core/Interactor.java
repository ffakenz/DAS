package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public interface Interactor<T> {

    InteractorResponse<T> execute(DynaActionForm form) throws Exception;
}