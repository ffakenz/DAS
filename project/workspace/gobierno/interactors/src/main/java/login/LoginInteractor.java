package login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import beans.LogInForm;
import beans.UsuarioForm;
import core.InteractorResponse;
import core.ResponseForward;
import login.boundaries.LogIn;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class LoginInteractor implements LogIn {

    public Function<Dao, Optional<Long>> isLoggedIn(final LogInForm form) {
        return loginDao -> {
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
        };
    }

    @Override
    public Consumer<Dao> logout(final LogInForm req) {
        return loginDao -> {
            try {
                loginDao.update(req);
            } catch (final SQLException ex) {
                ex.printStackTrace();
            }
        };
    }

    @Override
    public Function<Dao, Optional<Long>> login(final LogInForm form) {
        return loginDao -> {
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
        };
    }

    // is there any usuario in the repository suchthat is equals to the one sent by parameter ?
    @Override
    public Function<Dao, Boolean> validarUsuario(final UsuarioForm user) {
        return msUsuariosDao -> {
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
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(final DynaActionForm form) {
        return (daoFactory) -> {
            final Dao dao = daoFactory.apply("Usuarios", "login");
            final Dao logInDao = daoFactory.apply("LogIn", "login");

            final Optional<InteractorResponse> respuesta =
                    form.getItem("username").flatMap(u -> {
                        return form.getItem("password").map(p -> {
                            final UsuarioForm usr = new UsuarioForm();
                            usr.setUsername(u);
                            usr.setPassword(p);
                            final LoginInteractor auth = new LoginInteractor();


                            final InteractorResponse response = new InteractorResponse();
                            // is user valid ?
                            if (auth.validarUsuario(usr).apply(dao)) {
                                final LogInForm logInForm = new LogInForm();
                                logInForm.setUsername(u);
                                // is user logged in ?
                                auth.isLoggedIn(logInForm).apply(logInDao).ifPresent(loginId -> {
                                    logInForm.setId(loginId);
                                    logInForm.setLogoutTime(new Date(System.currentTimeMillis()));
                                    auth.logout(logInForm).accept(logInDao);
                                });

                                final Optional<Long> LogInId = auth.login(logInForm).apply(logInDao);
                                response.setResult(LogInId);

                                if (LogInId.isPresent()) response.setResponse(ResponseForward.SUCCESS);
                                else response.setResponse(ResponseForward.FAILURE);
                            } else {
                                response.setResponse(ResponseForward.FAILURE);
                                response.setResult(Optional.empty());
                            }
                            return response;
                        });
                    });

            final InteractorResponse response = new InteractorResponse();
            response.setResponse(ResponseForward.WARNING);
            response.setResult(Optional.empty());

            return respuesta.orElse(response); // Some error occur with username / password
        };
    }
}
