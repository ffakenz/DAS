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
        final String NOT_FOUND = "NOT_FOUND";
        final String documento = form.getItem("documento").orElse(NOT_FOUND);
        final String username = form.getItem("username").orElse(NOT_FOUND);
        final String password = form.getItem("password").orElse(NOT_FOUND);
        final UsuarioRol rol = UsuarioRol.CONSUMER;

        // Some error occur with documento /username / password
        if (documento.equals(NOT_FOUND) || username.equals(NOT_FOUND) || password.equals(NOT_FOUND))
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        final ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(Long.valueOf(documento));

        if (!consumerManager.getDao().selectConsumerByDni(consumerForm))
            return new InteractorResponse<>(ResponseForward.FAILURE, false);

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);
        usuarioForm.setPassword(password);
        usuarioForm.setRol(rol.toString());

        usuarioManager.createUser(usuarioForm);

        // if we are here it means the user was successfully created
        consumerForm.setUsername(username);
        consumerManager.getDao().addUsername(consumerForm);

        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
