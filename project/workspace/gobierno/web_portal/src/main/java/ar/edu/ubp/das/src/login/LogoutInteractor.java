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


public class LogoutInteractor implements Interactor<Long> {

    private LoginManager loginManager;
    private UsuarioManager usuarioManager;

    public LogoutInteractor(final DaoImpl loginDao, final DaoImpl msUsuariosDao) {
        this.loginManager = new LoginManager(loginDao);
        this.usuarioManager = new UsuarioManager(msUsuariosDao);
    }

    @Override
    public InteractorResponse execute(final DynaActionForm form) {
        final Optional<InteractorResponse> response =
                form.getItem("username").flatMap(u ->
                        form.getItem("id").map(id -> {

                            try {
                                final LogInForm logInForm = new LogInForm(u);
                                logInForm.setId(Long.parseLong(id));

                                loginManager.logout(logInForm);
                                // if we are here it means the logout was successfully or the user was already logged out
                                return new InteractorResponse(ResponseForward.SUCCESS);

                            } catch (final SQLException e) {
                                e.printStackTrace();
                                return new InteractorResponse(ResponseForward.FAILURE);
                            }
                        })
                );

        return response.orElse(new InteractorResponse(ResponseForward.WARNING));
    }

}
