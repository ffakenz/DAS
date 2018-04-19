package ar.edu.ubp.das.src.admin.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class AdminLoginAction implements Action {

    @Override
    public Function<BiFunction<String, String, Dao>, ForwardConfig> execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
                                                                          HttpServletResponse response) throws RuntimeException {

        return (daoFactory) -> mapping.getForwardByName( "success" );
    }
}
