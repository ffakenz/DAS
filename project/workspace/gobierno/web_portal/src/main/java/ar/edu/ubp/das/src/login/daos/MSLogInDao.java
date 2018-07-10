package ar.edu.ubp.das.src.login.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSLogInDao extends DaoImpl {
    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        final LogInForm logInForm = new LogInForm();
        logInForm.setId(result.getLong("id"));
        logInForm.setUsername(result.getString("username"));
        logInForm.setLoginTime(result.getDate("log_in_time"));
        logInForm.setLogoutTime(result.getDate("log_out_time"));
        return logInForm;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_login(?)");
        this.setParameter(1, ((LogInForm) form).getUsername());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_out(?, ?)");
        this.setParameter(1, ((LogInForm) form).getId());
        this.setParameter(2, ((LogInForm) form).getLogoutTime());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {
    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_logins", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<DynaActionForm> logins = this.executeQuery();
        this.disconnect();
        return logins;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}
