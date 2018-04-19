package ar.edu.ubp.das.src.login.interactors;

import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.Interactor;
import ar.edu.ubp.das.src.InteractorResponse;
import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.boundaries.ValidarUsuario;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LoginInteractor implements Interactor, ValidarUsuario, LogIn {
    @Override
    public Function<MSLogInDao, Optional<Long>> login(LogInForm req) {
        return loginDao -> {
            try {
                loginDao.insert(req);
                Optional<Long> max =
                        loginDao.select(null).stream()
                                .filter( l -> ((LogInForm)l).getUsername().equals(req.getUsername()) )
                                .map( l -> ((LogInForm) l).getId())
                                .max(Comparable::compareTo);

                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }
    // is there any usuario in the repository suchthat is equals to the one sent by parameter ?
    @Override
    public Function<MSUsuariosDao, Boolean> validarUsuario(UsuarioForm user) {
        return msUsuariosDao -> {
            try {
                return msUsuariosDao .select(null).stream().anyMatch( usr -> {
                    return ((UsuarioForm) usr).getUsername().equals(user.getUsername()) &&
                            ((UsuarioForm) usr).getPassword().equals(user.getPassword());
                });
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(ActionMapping mapping, DynaActionForm form) {
        return (daoFactory) -> {
            MSUsuariosDao dao = (MSUsuariosDao) daoFactory.apply("Usuarios", "login");
            MSLogInDao logInDao = (MSLogInDao) daoFactory.apply("LogIn", "login");

            Optional<InteractorResponse> respuesta =
                    form.getItem( "username").flatMap( u -> {
                        return form.getItem( "password").map( p -> {
                            UsuarioForm usr = new UsuarioForm();
                            usr.setUsername(u);
                            usr.setPassword(p);
                            LoginInteractor auth = new LoginInteractor();
                            Boolean isUserValid = auth.validarUsuario(usr).apply(dao);

                            if(isUserValid) {
                                LogInForm logInForm = new LogInForm();
                                logInForm.setUsername(u);
                                Optional<Long> LogInId = auth.login(logInForm).apply(logInDao);

                                InteractorResponse response = new InteractorResponse();
                                response.setForwardConfig(mapping.getForwardByName( "success" ));
                                response.setResult(LogInId);
                                return response; // LogIn Successfully
                            } else {
                                InteractorResponse response = new InteractorResponse();
                                response.setForwardConfig(mapping.getForwardByName( "failure" ));
                                response.setResult(Optional.empty());
                                return response; // LogIn Failed
                            }
                        });
                    });

            InteractorResponse response = new InteractorResponse();
            response.setForwardConfig(mapping.getForwardByName( "warning" ));
            response.setResult(Optional.empty());

            return respuesta.orElse(response); // Some error occur with username / password
        };
    }
}
