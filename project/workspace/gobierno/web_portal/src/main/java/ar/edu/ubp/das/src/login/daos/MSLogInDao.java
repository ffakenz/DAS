package ar.edu.ubp.das.src.login.daos;


import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSLogInDao extends DaoImpl<LogInForm> {

    public MSLogInDao() {
        super(LogInForm.class);
    }

    @Override
    public void insert(final LogInForm f) throws SQLException {
        executeProcedure("dbo.log_login(?)", f, "username");
    }

    @Override
    public void update(final LogInForm f) throws SQLException {
        executeProcedure("dbo.log_out(?)", f, "id");
    }

    @Override
    public void delete(final LogInForm form) throws SQLException {
    }

    @Override
    public List<LogInForm> select(final LogInForm form) throws SQLException {
        return executeQueryProcedure("dbo.get_logins(?)", form, "documento");
    }

    @Override
    public boolean valid(final LogInForm form) throws SQLException {
        return false;
    }

    public List<LogInForm> selectUserLoggIn(final LogInForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_login_by_username(?)", form, "username");
    }


    public void logoutByUsername(final LogInForm f) throws SQLException {
        executeProcedure("dbo.log_out_by_username(?)", f, "username");
    }

    // Needed For Tests Purpose
    public Optional<LogInForm> selectLastUserLogin(final LogInForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_last_login_by_username(?)", form, "username").stream().findFirst();
    }
}
