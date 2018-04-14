package ar.edu.ubp.das.src.boundries;

import ar.edu.ubp.das.src.boundries.requests.LogInReq;
import ar.edu.ubp.das.src.boundries.responses.LogInResp;

public interface LogIn {
    LogInResp logIn(LogInReq req);
}
