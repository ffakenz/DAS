package ar.edu.ubp.das.src.usuarios;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import ar.edu.ubp.das.src.usuarios.model.UsuarioManager;

import java.sql.SQLException;

public class UsuarioUpdateInteractor implements Interactor<Boolean> {

    private UsuarioManager usuarioManager;

    public UsuarioUpdateInteractor(final DaoImpl msUsuariosDao) {
        this.usuarioManager = new UsuarioManager(msUsuariosDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException, NumberFormatException {
        final Pair<String, Boolean> documento = form.isItemValid("documento");
        final Pair<String, Boolean> username = form.isItemValid("username");
        final Pair<String, Boolean> password = form.isItemValid("password");

        // Some error occur with documento /username / password
        if (!documento.snd || !username.snd || !password.snd)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        final UsuarioForm usuarioForm = form.convertTo(UsuarioForm.class);

        if (!usuarioManager.verifyDocumento(usuarioForm.getDocumento()))
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        usuarioManager.update(usuarioForm);

        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
