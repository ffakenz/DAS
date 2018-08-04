package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.daos.decorators.MSLoginDaoDec;
import ar.edu.ubp.das.src.login.daos.decorators.MSUsuariosDaoDec;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class LoginInteractor {

    private final MSUsuariosDaoDec msUsuariosDao;
    private final MSLoginDaoDec loginDao;

    public LoginInteractor(final Dao loginDao, final Dao msUsuariosDao) {
        this.loginDao = new MSLoginDaoDec(loginDao);
        this.msUsuariosDao = new MSUsuariosDaoDec(msUsuariosDao);
    }

    public Boolean isLoggedIn(final LogInForm form) {
        try {
            return !loginDao.select(form).isEmpty();
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void logout(final LogInForm req) {
        try {
            loginDao.update(req);
        } catch (final SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void login(final LogInForm form) {
        try {
            loginDao.insert(form);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean isValidUsuario(final UsuarioForm user) {
        try {
            return !msUsuariosDao.selectByUserNameAndPassword(user).isEmpty();
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public InteractorResponse execute(final DynaActionForm form) {

        final Optional<UsuarioForm> usr =
                form.getItem("username").flatMap(u ->
                        form.getItem("password").map(p ->
                                new UsuarioForm(u, p)
                        )
                );

        final Optional<InteractorResponse> response =
                usr.map(u -> {
                    // is user valid ?
                    if (!isValidUsuario(u)) {
                        return new InteractorResponse(ResponseForward.FAILURE);
                    }

                    final LogInForm logInForm = new LogInForm(u.getUsername());
                    // is user logged in ?
                    isLoggedIn(logInForm).ifPresent(loginId -> {
                        logInForm.setId(loginId);
                        logInForm.setLogoutTime(new Timestamp(System.currentTimeMillis()));
                        logout(logInForm);
                    });

                    return login(logInForm)
                            .map(LogInId -> new InteractorResponse(ResponseForward.SUCCESS, LogInId))
                            .orElse(new InteractorResponse(ResponseForward.FAILURE));
                });


        return response.orElse(new InteractorResponse(ResponseForward.WARNING)); // Some error occur with username / password
    }
}
