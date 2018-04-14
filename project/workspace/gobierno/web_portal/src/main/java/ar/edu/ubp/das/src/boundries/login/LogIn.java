package ar.edu.ubp.das.src.boundries.login;

import ar.edu.ubp.das.src.boundries.login.LogInReq;
import ar.edu.ubp.das.src.boundries.login.LogInResp;

public interface LogIn {
    LogInResp logIn(LogInReq req);
}
