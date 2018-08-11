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
    public InteractorResponse<Long> execute(final DynaActionForm form) {
        final Optional<InteractorResponse> response =
                form.getItem("username").flatMap(u ->
                        form.getItem("password").map(p -> {
                            // is user valid ?
                            try {
                                if (!usuarioManager.verifyUsernameAndPassword(u, p)) {
                                    return new InteractorResponse(ResponseForward.FAILURE);
                                }

                                final LogInForm logInForm = new LogInForm(u);

                                if (loginManager.isLoggedIn(logInForm).isPresent()) {
                                    final Long loginId = loginManager.isLoggedIn(logInForm).get();
                                    logInForm.setId(loginId);
                                    loginManager.logout(logInForm);
                                }

                                return loginManager.login(logInForm)
                                        .map(LogInId -> new InteractorResponse(ResponseForward.SUCCESS, Optional.of(LogInId)))
                                        .orElse(new InteractorResponse(ResponseForward.FAILURE));

                            } catch (final SQLException e) {
                                e.printStackTrace();
                                return new InteractorResponse(ResponseForward.FAILURE);
                            }
                        })
                );

        return response.orElse(new InteractorResponse(ResponseForward.WARNING)); // Some error occur with username / password
    }

}
