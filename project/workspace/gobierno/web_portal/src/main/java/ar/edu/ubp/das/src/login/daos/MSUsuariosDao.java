package ar.edu.ubp.das.src.login.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioRol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSUsuariosDao extends DaoImpl<UsuarioForm> {

    @Override
    public UsuarioForm make(final ResultSet result) throws SQLException {
        final UsuarioRol rol = UsuarioRol.getRol(result.getString("rol"));
        return new UsuarioForm(result.getString("username"),
                result.getString("password"),
                rol
        );
    }

    @Override
    public void insert(final UsuarioForm form) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(final UsuarioForm form) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(final UsuarioForm form) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public List<UsuarioForm> select(final UsuarioForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_usuarios", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<UsuarioForm> usuarios = this.executeQuery();
        this.disconnect();
        return usuarios;
    }

    @Override
    public boolean valid(final UsuarioForm form) throws SQLException {
        // TODO Auto-generated method stub
        return true;
    }
}
