package login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;
import login.boundaries.LogIn;
import login.forms.LogInForm;
import login.forms.UsuarioForm;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LoginInteractor implements Interactor, LogIn {

    private Function<Dao, Boolean> isLoggedIn(LogInForm form) {
        return loginDao -> {
            try {
                return
                    loginDao.select(null).stream().anyMatch( l -> {
                        LogInForm login = (LogInForm) l;
                        return
                            login.getUsername().equals(form.getUsername()) &&
                                login.getLogoutTime() == null;
                    });
            } catch(SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        };
    }

    @Override
    public Function<Dao, Optional<Long>> login(LogInForm form) {
        return loginDao -> {
            try {
                if(!isLoggedIn(form).apply(loginDao)) {
                    loginDao.insert(form);
                    Optional<Long> max =
                            loginDao.select(null).stream()
                                    .filter( l -> ((LogInForm)l).getUsername().equals(form.getUsername()) )
                                    .map( l -> ((LogInForm) l).getId())
                                    .max(Comparable::compareTo);

                    return max;
                } else {
                    return Optional.empty();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }
    // is there any usuario in the repository suchthat is equals to the one sent by parameter ?
    @Override
    public Function<Dao, Boolean> validarUsuario(UsuarioForm user) {
        return msUsuariosDao -> {
            try {
                return msUsuariosDao .select(null).stream().anyMatch( usr -> {
                    UsuarioForm dbUser = ((UsuarioForm) usr);
                    return dbUser.getUsername().equals(user.getUsername()) &&
                            dbUser.getPassword().equals(user.getPassword());
                });
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return (daoFactory) -> {
            Dao dao = daoFactory.apply("Usuarios", "login");
            Dao logInDao = daoFactory.apply("LogIn", "login");

            Optional<InteractorResponse> respuesta =
                    form.getItem( "username").flatMap( u -> {
                        return form.getItem( "password").map( p -> {
                            UsuarioForm usr = new UsuarioForm();
                            usr.setUsername(u);
                            usr.setPassword(p);
                            LoginInteractor auth = new LoginInteractor();
                            Boolean isUserValid = auth.validarUsuario(usr).apply(dao);

                            InteractorResponse response = new InteractorResponse();
                            if(isUserValid) {
                                LogInForm logInForm = new LogInForm();
                                logInForm.setUsername(u);
                                Optional<Long> LogInId = auth.login(logInForm).apply(logInDao);
                                response.setResult(LogInId);

                                if(LogInId.isPresent()) response.setResponse(ResponseForward.SUCCESS);
                                else response.setResponse(ResponseForward.FAILURE);
                            } else {
                                response.setResponse(ResponseForward.FAILURE);
                            }
                            return response;
                        });
                    });

            InteractorResponse response = new InteractorResponse();
            response.setResponse(ResponseForward.WARNING);
            response.setResult(Optional.empty());

            return respuesta.orElse(response); // Some error occur with username / password
        };
    }
}
