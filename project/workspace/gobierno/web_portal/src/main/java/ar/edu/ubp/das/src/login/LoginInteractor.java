package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.model.login.LoginManager;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioManager;

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
        final String NOT_FOUND = "NOT_FOUND";
        final String password = form.getItem("password").orElse(NOT_FOUND);
        final String username = form.getItem("username").orElse(NOT_FOUND);

        if (username.equals(NOT_FOUND) || password.equals(NOT_FOUND))
            return new InteractorResponse<>(ResponseForward.WARNING); // Some error occur with username / password

        if (!usuarioManager.verifyUsernameAndPassword(username, password))
            return new InteractorResponse<>(ResponseForward.FAILURE);

        final LogInForm logInForm = new LogInForm(username);
        final Optional<Long> loginId = loginManager.isLoggedIn(logInForm);
        if (loginId.isPresent()) {
            logInForm.setId(loginId.get());
            loginManager.logout(logInForm);
        }

        return loginManager.login(logInForm)
                .map(LogInId -> new InteractorResponse<>(ResponseForward.SUCCESS, LogInId))
                .orElse(new InteractorResponse<>(ResponseForward.FAILURE));
    }
}
