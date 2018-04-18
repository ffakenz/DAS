package ar.edu.ubp.das.src.login.interactors;

import ar.edu.ubp.das.src.login.boundaries.ValidarUsuario;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.function.Function;

public class ValidarUsuarioImpl implements ValidarUsuario {

    // is there any usuario in the repository suchthat is equals to the one sent by parameter ?
    @Override
    public Function<MSUsuariosDao, Boolean> validarUsuario(UsuarioForm user) {
        return msUsuariosDao -> {
            try {
                return msUsuariosDao .select(null).stream().anyMatch( usr -> {
                    return ((UsuarioForm) usr).getUsername() == user.getUsername() &&
                            ((UsuarioForm) usr).getPassword() == user.getPassword();
                });
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }
}
