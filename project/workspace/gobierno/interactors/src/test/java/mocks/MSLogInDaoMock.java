package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import beans.LogInForm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSLogInDaoMock implements Dao {
    public List<LogInForm> logins;

    public MSLogInDaoMock() {
        logins = new ArrayList<>();
    }

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        return null;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        final Optional<Long> max =
                logins.stream()
                        .filter(l -> l.getUsername().equals(((LogInForm) form).getUsername()))
                        .map(l -> l.getId())
                        .max(Comparable::compareTo);

        final LogInForm login = (LogInForm) form;
        login.setId(max.orElse(Long.valueOf(0)) + 1);
        logins.add(login);
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        logins.stream().filter(c -> {
            LogInForm formConc = (LogInForm) form;
            return c.getId() == formConc.getId();
        }).findFirst().ifPresent(c -> {
            logins.remove(c);
            final LogInForm newLogin = (LogInForm) form;
            newLogin.setLoginTime(c.getLoginTime());
            newLogin.setLogoutTime(new Date(System.currentTimeMillis()));
            logins.add((LogInForm) form);
        });
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        return logins.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}