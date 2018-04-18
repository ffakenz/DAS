package ar.edu.ubp.das.src.login.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MSLogInDao extends DaoImpl {
    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        LogInForm logInForm = new LogInForm();
        logInForm.setId(result.getLong("id"));
        logInForm.setUsername(result.getString("username"));
        logInForm.setLoginTime(result.getDate("loginTime"));
        logInForm.setLogoutTime(result.getDate("logoutTime"));
        return logInForm;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure( "dbo.log_login(?)" );
        this.setParameter( 1, ((LogInForm) form).getUsername());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {
    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {
    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_logins(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter( 1, ((LogInForm) form).getUsername());
        List<DynaActionForm> logins  = this.executeQuery();
        this.disconnect();
        return logins;
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
