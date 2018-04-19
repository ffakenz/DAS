package ar.edu.ubp.das.src.login.interactors;

import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public class LogInImpl implements LogIn {
    @Override
    public Function<MSLogInDao, Optional<Long>> login(LogInForm req) {
        return loginDao -> {
            try {
                loginDao.insert(req);
                Optional<Long> max =
                        loginDao.select(null).stream()
                            .filter( l -> ((LogInForm)l).getUsername().equals(req.getUsername()) )
                            .map( l -> ((LogInForm) l).getId())
                            .max(Comparable::compareTo);

                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }
}
