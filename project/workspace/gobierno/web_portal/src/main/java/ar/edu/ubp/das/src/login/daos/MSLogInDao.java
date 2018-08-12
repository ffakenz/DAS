package ar.edu.ubp.das.src.login.daos;


import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.LogInForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        executeProcedure("dbo.log_out(?, ?)", f, "id", "username");
    }

    @Override
    public void delete(final LogInForm form) throws SQLException {
    }

    @Override
    public List<LogInForm> select(final LogInForm f) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_logins(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, f.getUsername());
        final List<LogInForm> logins = this.executeQuery();
        this.disconnect();
        return logins;
    }

    @Override
    public boolean valid(final LogInForm form) throws SQLException {
        return false;
    }
}
