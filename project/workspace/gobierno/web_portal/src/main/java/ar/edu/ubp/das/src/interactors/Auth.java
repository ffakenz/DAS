package ar.edu.ubp.das.src.interactors;

import ar.edu.ubp.das.src.boundries.LogIn;
import ar.edu.ubp.das.src.boundries.requests.LogInReq;
import ar.edu.ubp.das.src.boundries.responses.LogInResp;

public class Auth implements LogIn {
    @Override
    public LogInResp logIn(LogInReq req) {



        return new LogInResp("c");
    }
}
