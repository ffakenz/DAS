package ar.edu.ubp.das.src.login.model.login;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.DaoExtender;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSLoginDaoEx extends DaoExtender<LogInForm> {
    public MSLoginDaoEx(final DaoImpl dao) {
        super(dao);
    }

    public List<LogInForm> selectUserLoggIn(final LogInForm form) throws SQLException {
        dao.connect();
        dao.setProcedure("dbo.get_login_by_username(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        dao.setParameter(1, form.getUsername());
        final List<LogInForm> logins = dao.executeQuery();
        dao.disconnect();
        return logins;
    }

    // Needed For Tests Purpose
    public List<LogInForm> selectLastUserLogin(final LogInForm form) throws SQLException {
        dao.connect();
        dao.setProcedure("dbo.get_last_login_by_username(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        dao.setParameter(1, form.getUsername());
        final List<LogInForm> logins = dao.executeQuery();
        dao.disconnect();
        return logins;
    }

}
