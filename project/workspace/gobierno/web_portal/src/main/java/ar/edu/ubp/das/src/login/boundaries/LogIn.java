package ar.edu.ubp.das.src.login.boundaries;

import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.util.function.Consumer;

public interface LogIn {
    Consumer<MSLogInDao> login(LogInForm req);
}

