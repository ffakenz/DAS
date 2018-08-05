package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.daos.extenders.MSLoginDaoEx;
import ar.edu.ubp.das.src.login.daos.extenders.MSUsuariosDaoEx;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.Optional;

public class LoginInteractor {

    private MSLoginDaoEx loginDao;
    private MSUsuariosDaoEx msUsuariosDao;

    public LoginInteractor(final DaoImpl loginDao, final DaoImpl msUsuariosDao) {
        this.loginDao = new MSLoginDaoEx(loginDao);
        this.msUsuariosDao = new MSUsuariosDaoEx(msUsuariosDao);
    }

    public Optional<Long> isLoggedIn(final LogInForm form) throws SQLException {
        return loginDao.selectUserLoggIn(form).stream().findFirst().map(l -> l.getId());
    }

    public void logout(final LogInForm req) throws SQLException {
        loginDao.update(req);
    }

    public Optional<Long> login(final LogInForm form) throws SQLException {
        loginDao.insert(form);
        return isLoggedIn(form);
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean isValidUsuario(final UsuarioForm user) throws SQLException {
        return !msUsuariosDao.selectByUserNameAndPassword(user).isEmpty();
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
                    try {
                        if (!isValidUsuario(u)) {
                            return new InteractorResponse(ResponseForward.FAILURE);
                        }

                        final LogInForm logInForm = new LogInForm(u.getUsername());

                        if (isLoggedIn(logInForm).isPresent()) {
                            final Long loginId = isLoggedIn(logInForm).get();
                            logInForm.setId(loginId);
                            logout(logInForm);
                        }

                        return login(logInForm)
                                .map(LogInId -> new InteractorResponse(ResponseForward.SUCCESS, LogInId))
                                .orElse(new InteractorResponse(ResponseForward.FAILURE));

                    } catch (final SQLException e) {
                        e.printStackTrace();
                        return new InteractorResponse(ResponseForward.FAILURE);
                    }
                });

        return response.orElse(new InteractorResponse(ResponseForward.WARNING)); // Some error occur with username / password
    }

}
