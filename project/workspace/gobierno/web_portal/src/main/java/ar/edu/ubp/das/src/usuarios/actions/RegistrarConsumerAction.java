package ar.edu.ubp.das.src.usuarios.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.usuarios.UsuarioUpdateInteractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.USUARIOS_DAO_NAME;
import static ar.edu.ubp.das.src.utils.Constants.USUARIOS_DAO_PACKAGE;

// This respond to the First Login USE CASE for the consumer
public class RegistrarConsumerAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping,
                                 final DynaActionForm form,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)

            throws SQLException, RuntimeException {


        final DaoImpl msUsuariosDao = DaoFactory.getDao(USUARIOS_DAO_NAME, USUARIOS_DAO_PACKAGE);

        final UsuarioUpdateInteractor usuarioUpdateInteractor = new UsuarioUpdateInteractor(msUsuariosDao);

        final InteractorResponse<Boolean> result = usuarioUpdateInteractor.execute(form);

        if (result.getResponse().getForward().equals(ResponseForward.WARNING.getForward())) {
            request.setAttribute("cabecera", "usuario_failure");
        } else if (result.getResponse().getForward().equals(ResponseForward.FAILURE.getForward())) {
            request.setAttribute("cabecera", "invalid_data");
        }

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
