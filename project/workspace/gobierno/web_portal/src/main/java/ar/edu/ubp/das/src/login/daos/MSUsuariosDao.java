package ar.edu.ubp.das.src.login.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSUsuariosDao extends DaoImpl {

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        return new UsuarioForm(result.getString("username"),
                result.getString("password"),
                result.getString("rol"));
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_usuarios", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<DynaActionForm> usuarios = this.executeQuery();
        this.disconnect();
        return usuarios;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        // TODO Auto-generated method stub
        return true;
    }
}
