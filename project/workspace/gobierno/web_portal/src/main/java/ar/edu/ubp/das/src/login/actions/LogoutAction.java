package ar.edu.ubp.das.src.login.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.LogoutInteractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.SSID;

public class LogoutAction implements Action {

    private static final Logger log = LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws SQLException, RuntimeException {

        final DaoImpl loginDao = DaoFactory.getDao("LogIn", "login");

        final HttpSession session = request.getSession();

        if (session.getAttribute(SSID) == null) {
            // jamas deberia acceder a esta parte del código
            // si se accede significa que alguien esta tratando de encontrar backdoors
            log.error("Se intenta cerrar una sesion y no hay ningun usuario logueado");
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }

        form.setItem(SSID, session.getAttribute(SSID).toString());
        final InteractorResponse<Long> result = new LogoutInteractor(loginDao).execute(form);

        session.removeAttribute(SSID);

        logAction(mapping, form, request, response);
        return mapping.getForwardByName(result.getResponse().getForward());
    }

}
