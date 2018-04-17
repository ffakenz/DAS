package ar.edu.ubp.das.src.login.interactors;

import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.SQLException;
import java.util.function.Consumer;

public class LogInImpl implements LogIn {
    @Override
    public Consumer<MSLogInDao> login(LogInForm req) {
        return loginDao -> {
            try {
                loginDao.insert(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }
}
