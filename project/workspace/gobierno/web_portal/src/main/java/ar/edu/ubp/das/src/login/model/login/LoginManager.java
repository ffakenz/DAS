package ar.edu.ubp.das.src.login.model.login;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.Manager;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.SQLException;
import java.util.Optional;

public class LoginManager extends Manager<MSLogInDao> {

    public LoginManager(final DaoImpl dao) {
        super(dao);
    }

    public Optional<Long> isLoggedIn(final LogInForm form) throws SQLException {
        return dao.selectUserLoggIn(form).stream().findFirst().map(l -> l.getId());
    }

    public void logout(final LogInForm form) throws SQLException {
        dao.update(form);
    }

    public Optional<Long> login(final LogInForm form) throws SQLException {
        dao.insert(form);
        return isLoggedIn(form);
    }
}
