package ar.edu.ubp.das.src.login.boundaries;

import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.util.Optional;
import java.util.function.Function;

public interface LogIn {
    Function<MSLogInDao, Optional<Long>> login(LogInForm req);
}

