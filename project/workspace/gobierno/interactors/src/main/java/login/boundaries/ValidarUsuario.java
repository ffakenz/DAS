package login.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import login.forms.UsuarioForm;

import java.util.function.Function;

public interface ValidarUsuario {
    Function<Dao, Boolean> validarUsuario(UsuarioForm req);
}
