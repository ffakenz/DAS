package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.daos.extenders.MSLoginDaoEx;
import ar.edu.ubp.das.src.login.daos.extenders.MSUsuariosDaoEx;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.Optional;

public class LoginInteractor {

    private final MSUsuariosDaoEx msUsuariosDao;
    private final MSLoginDaoEx loginDao;

    public LoginInteractor(final Dao loginDao, final Dao msUsuariosDao) {
        this.loginDao = new MSLoginDaoEx(loginDao);
        this.msUsuariosDao = new MSUsuariosDaoEx(msUsuariosDao);
    }

    public Optional<Long> isLoggedIn(final LogInForm form) {
        try {
            return loginDao.selectUserLoggIn(form).stream().findFirst().map(l -> l.getId());
        } catch (final SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void logout(final LogInForm req) {
        try {
            loginDao.update(req);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Long> login(final LogInForm form) {
        try {
            loginDao.insert(form);
            return isLoggedIn(form);
        } catch (final SQLException e) {
            e.printStackTrace();
            return Optional.empty();
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

                    isLoggedIn(logInForm).ifPresent(loginId -> {
                        logInForm.setId(loginId);
                        logout(logInForm);
                    });

                    return login(logInForm)
                            .map(LogInId -> new InteractorResponse(ResponseForward.SUCCESS, LogInId))
                            .orElse(new InteractorResponse(ResponseForward.FAILURE));
                });

        return response.orElse(new InteractorResponse(ResponseForward.WARNING)); // Some error occur with username / password
    }
}
