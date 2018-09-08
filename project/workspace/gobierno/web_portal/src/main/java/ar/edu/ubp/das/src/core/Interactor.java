package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.SQLException;

public interface Interactor<T> {
    InteractorResponse<T> execute(DynaActionForm form) throws SQLException;
}
