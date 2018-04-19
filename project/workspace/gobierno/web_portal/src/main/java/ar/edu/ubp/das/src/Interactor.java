package ar.edu.ubp.das.src;

import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Interactor {

    Function<BiFunction<String, String, Dao>, InteractorResponse> execute(ActionMapping mapping, DynaActionForm form);
}
