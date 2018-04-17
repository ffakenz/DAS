package ar.edu.ubp.das.src.login.boundaries;

import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.util.function.Function;

public interface ValidarUsuario {
    Function<MSUsuariosDao, Boolean> validarUsuario(UsuarioForm req);
}
