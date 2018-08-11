package ar.edu.ubp.das.src.login.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.login.LoginInteractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class LoginAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws SQLException, RuntimeException {


        final DaoImpl msUsuariosDao = DaoFactory.getDao("Usuarios", "login");
        final DaoImpl loginDao = DaoFactory.getDao("LogIn", "login");

        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);
        final InteractorResponse result = action.execute(form);

        final Optional<Long> logInId = result.getResult();

        final HttpSession session = request.getSession();
        session.setAttribute("LogInId", logInId);

        return mapping.getForwardByName(result.getResponse().getForward());
    }

}
