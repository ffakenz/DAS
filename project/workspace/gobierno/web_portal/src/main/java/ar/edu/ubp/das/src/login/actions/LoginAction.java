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

import static ar.edu.ubp.das.src.utils.Constants.SSID;
import static ar.edu.ubp.das.src.utils.Constants.USER_TYPE;

public class LoginAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LoginAction.class);


    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws SQLException, RuntimeException {

        final DaoImpl msUsuariosDao = DaoFactory.getDao("Usuarios", "usuarios");
        final DaoImpl loginDao = DaoFactory.getDao("LogIn", "login");

        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);
        final InteractorResponse<Long> result = action.execute(form);

        if(result.getResponse().getForward().equals(ResponseForward.SUCCESS.getForward())) {

            final HttpSession session = request.getSession();

            // seteamos como ssid el loginId obtenido de la tabla login
            session.setAttribute(SSID, result.getResult());
            request.setAttribute(USER_TYPE, form.getItem(USER_TYPE).orElse(""));

            log.info("[ssid:{}][user_type:{}]", result.getResult(), form.getItem(USER_TYPE).orElse(""));
        }

        return mapping.getForwardByName(result.getResponse().getForward());
    }

}
