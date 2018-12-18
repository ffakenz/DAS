package ar.edu.ubp.das.src.usuarios.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.usuarios.UsuarioUpdateInteractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class RegistrarConsumerAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping,
                                 final DynaActionForm form,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)

            throws SQLException, RuntimeException {


        final DaoImpl msUsuariosDao = DaoFactory.getDao(USUARIOS_DAO_NAME, USUARIOS_DAO_PACKAGE);

        final UsuarioUpdateInteractor usuarioUpdateInteractor = new UsuarioUpdateInteractor(msUsuariosDao);

        InteractorResponse<Boolean> resp = usuarioUpdateInteractor.execute(form);

        return mapping.getForwardByName(resp.getResponse().getForward());
    }
}
