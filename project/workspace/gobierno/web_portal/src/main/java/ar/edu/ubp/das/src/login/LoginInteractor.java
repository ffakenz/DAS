package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class LoginInteractor {

    private final Dao msUsuariosDao;
    private final Dao loginDao;

    public LoginInteractor(final Dao loginDao, final Dao msUsuariosDao) {
        this.loginDao = loginDao;
        this.msUsuariosDao = msUsuariosDao;
    }

    public Optional<Long> isLoggedIn(final LogInForm form) {
        try {
            return
                    loginDao.select(null).stream().filter(l -> {
                        LogInForm login = (LogInForm) l;
                        return
                                login.getUsername().equals(form.getUsername()) &&
                                        login.getLogoutTime() == null;
                    }).findFirst().map(l -> {
                        final LogInForm lf = (LogInForm) l;
                        return lf.getId();
                    });
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public void logout(final LogInForm req) {
        try {
            loginDao.update(req);
        } catch (final SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Optional<Long> login(final LogInForm form) {
        try {
            loginDao.insert(form);
            final Optional<Long> max =
                    loginDao.select(null).stream()
                            .filter(l -> ((LogInForm) l).getUsername().equals(form.getUsername()))
                            .map(l -> ((LogInForm) l).getId())
                            .max(Comparable::compareTo);
            return max;
        } catch (final SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean validarUsuario(final UsuarioForm user) {
        try {
            return msUsuariosDao.select(null).stream().anyMatch(usr -> {
                final UsuarioForm dbUser = ((UsuarioForm) usr);
                return dbUser.getUsername().equals(user.getUsername()) &&
                        dbUser.getPassword().equals(user.getPassword());
            });
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
                    if (!validarUsuario(u)) {
                        return new InteractorResponse(ResponseForward.FAILURE);
                    }

                    final LogInForm logInForm = new LogInForm(u.getUsername());
                    // is user logged in ?
                    isLoggedIn(logInForm).ifPresent(loginId -> {
                        logInForm.setId(loginId);
                        logInForm.setLogoutTime(new Date(System.currentTimeMillis()));
                        logout(logInForm);
                    });

                    return login(logInForm)
                            .map(LogInId -> new InteractorResponse(ResponseForward.SUCCESS, LogInId))
                            .orElse(new InteractorResponse(ResponseForward.FAILURE));
                });


        return response.orElse(new InteractorResponse(ResponseForward.WARNING)); // Some error occur with username / password
    }
}
