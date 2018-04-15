package ar.edu.ubp.das.src.login.boundaries;

import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;

import java.util.function.Function;

public interface LogIn {
    Function<MSUsuariosDao, LogInResp> logIn(LogInReq req);
}
