package login.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import login.forms.LogInForm;
import login.forms.UsuarioForm;

import java.util.Optional;
import java.util.function.Function;

public interface LogIn {
    Function<Dao, Optional<Long>> login(LogInForm req);
    Function<Dao, Boolean> validarUsuario(UsuarioForm req);
}

