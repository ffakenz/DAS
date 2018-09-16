package ar.edu.ubp.das.src.usuarios;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import ar.edu.ubp.das.src.usuarios.model.UsuarioManager;
import ar.edu.ubp.das.src.usuarios.model.UsuarioRol;
import com.sun.tools.javac.util.Pair;

import java.sql.SQLException;

public class UsuarioCreateInteractor implements Interactor<Boolean> {

    private UsuarioManager usuarioManager;
    private ConsumerManager consumerManager;

    public UsuarioCreateInteractor(final DaoImpl msUsuariosDao, final DaoImpl msConsumerDao) {
        this.usuarioManager = new UsuarioManager(msUsuariosDao);
        this.consumerManager = new ConsumerManager(msConsumerDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException, NumberFormatException {
        final Pair<String, Boolean> documento = isItemValid("documento", form);
        final Pair<String, Boolean> username = isItemValid("username", form);
        final Pair<String, Boolean> password = isItemValid("password", form);
        final UsuarioRol rol = UsuarioRol.CONSUMER;

        // Some error occur with documento /username / password
        if (!documento.snd || !username.snd || !password.snd)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        final ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(Long.valueOf(documento.fst));

        if (!consumerManager.getDao().selectConsumerByDni(consumerForm))
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username.fst);
        usuarioForm.setPassword(password.fst);
        usuarioForm.setRol(rol.toString());

        usuarioManager.createUser(usuarioForm);

        // if we are here it means the user was successfully created
        consumerForm.setUsername(username.fst);
        consumerManager.getDao().addUsername(consumerForm);

        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
