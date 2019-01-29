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
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.FORWARD_NAME;
import static ar.edu.ubp.das.src.utils.Constants.SSID;

public class LoginAction implements Action {

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

        if (result.getResponse().getForward().equals(ResponseForward.SUCCESS.getForward())) {

            // si estamos en success nunca debería fallar excepto esté caida la db
            final UsuarioForm usuarioForm = ((MSUsuariosDao) msUsuariosDao).selectByUserNameAndPassword(
                    form.getItem("username").get(),
                    form.getItem("password").get()).get();

            final String forwardName = result.getResponse().getForward() + "_" + usuarioForm.getRol();

            // seteamos como ssid el loginId obtenido de la tabla login
            session.setAttribute(SSID, result.getResult());
            session.setAttribute(FORWARD_NAME, forwardName);

            log.info("[ssid:{}][user_type:{}][forward_name:{}]", result.getResult(), usuarioForm.getRol(), forwardName);
            return mapping.getForwardByName(forwardName);
        }

        logAction(mapping, form, request, response);
        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
