package ar.edu.ubp.das.src.login.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.LoginInteractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class LoginAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);


    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws SQLException, RuntimeException {

        final HttpSession session = request.getSession();

        if (session != null && session.getAttribute(SSID) != null && session.getAttribute(FORWARD_NAME) != null) {
            // significa que ya tenemos un usuario logueado y no debemos volver a loguearlo
            return mapping.getForwardByName(session.getAttribute(FORWARD_NAME).toString());
        }

        final DaoImpl msUsuariosDao = DaoFactory.getDao("Usuarios", "usuarios");
        final DaoImpl loginDao = DaoFactory.getDao("LogIn", "login");

        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);
        final InteractorResponse<Long> result = action.execute(form);

        final Optional<String> userType = form.getItem(USER_TYPE);

        if (result.getResponse().getForward().equals(ResponseForward.SUCCESS.getForward()) ||
                userType.isPresent()) {
            // seteamos como ssid el loginId obtenido de la tabla login
            final String forwardName = result.getResponse().getForward() + "_" + userType.get();

            session.setAttribute(SSID, result.getResult());
            session.setAttribute(FORWARD_NAME, forwardName);

            log.info("[ssid:{}][user_type:{}][forward_name:{}]", result.getResult(), userType.get(), forwardName);
            return mapping.getForwardByName(forwardName);
        }

        logAction(mapping, form, request, response);
        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
