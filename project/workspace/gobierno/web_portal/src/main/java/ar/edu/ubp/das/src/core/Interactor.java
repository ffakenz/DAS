package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import com.sun.tools.javac.util.Pair;

public interface Interactor<T> {

    default Pair<String, Boolean> isItemValid(final String arg, final DynaActionForm form) {
        final String NOT_FOUND = "NOT_FOUND";
        final String itemValue = form.getItem(arg).orElse(NOT_FOUND);
        return Pair.of(itemValue, !itemValue.equals(NOT_FOUND));
    }

    InteractorResponse<T> execute(DynaActionForm form) throws Exception;
}