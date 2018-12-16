package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.model.LoginManager;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import ar.edu.ubp.das.src.usuarios.model.UsuarioManager;

import java.sql.SQLException;
import java.util.Optional;


public class LoginInteractor implements Interactor<Long> {

    private LoginManager loginManager;
    private UsuarioManager usuarioManager;

    public LoginInteractor(final DaoImpl loginDao, final DaoImpl msUsuariosDao) {
        this.loginManager = new LoginManager(loginDao);
        this.usuarioManager = new UsuarioManager(msUsuariosDao);
    }

    @Override
    public InteractorResponse<Long> execute(final DynaActionForm form) throws SQLException {
        final Pair<String, Boolean> username = form.isItemValid("username");
        final Pair<String, Boolean> password = form.isItemValid("password");

        if (!username.snd || !password.snd)
            return new InteractorResponse<>(ResponseForward.WARNING); // Some error occur with username / password

        Optional<UsuarioForm> usuarioForm = usuarioManager.verifyUsernameAndPassword(username.fst, password.fst);

        if (usuarioForm.isPresent()) {

            LogInForm logInForm = form.convertTo(LogInForm.class);
            logInForm.setDocumento(usuarioForm.get().getDocumento());

            return loginManager.login(logInForm)
                .map(LogInId -> new InteractorResponse<>(ResponseForward.SUCCESS, LogInId))
                .orElse(new InteractorResponse<>(ResponseForward.FAILURE));
        }

        return new InteractorResponse<>(ResponseForward.FAILURE);
    }
}
