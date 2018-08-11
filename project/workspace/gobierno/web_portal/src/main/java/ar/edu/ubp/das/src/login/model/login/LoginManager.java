package ar.edu.ubp.das.src.login.model.login;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.SQLException;
import java.util.Optional;

public class LoginManager {

    MSLoginDaoEx msloginDao;

    public LoginManager(final DaoImpl msloginDao) {
        this.msloginDao = new MSLoginDaoEx(msloginDao);
    }

    public Optional<Long> isLoggedIn(final LogInForm form) throws SQLException {
        return msloginDao.selectUserLoggIn(form).stream().findFirst().map(l -> l.getId());
    }

    public void logout(final LogInForm form) throws SQLException {
        msloginDao.update(form);
    }

    public Optional<Long> login(final LogInForm form) throws SQLException {
        msloginDao.insert(form);
        return isLoggedIn(form);
    }
}
