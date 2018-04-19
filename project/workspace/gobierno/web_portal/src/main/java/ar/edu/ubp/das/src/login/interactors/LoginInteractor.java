package ar.edu.ubp.das.src.login.interactors;

import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.boundaries.ValidarUsuario;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public class LoginInteractor implements ValidarUsuario, LogIn {
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
}
