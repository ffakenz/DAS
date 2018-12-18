package ar.edu.ubp.das.src.usuarios.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ActualizarConsumerAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping,
                                 final DynaActionForm form,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)

            throws SQLException, RuntimeException {

        return null;
    }
}
