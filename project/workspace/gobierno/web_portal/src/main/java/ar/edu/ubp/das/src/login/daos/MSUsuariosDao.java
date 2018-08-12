package ar.edu.ubp.das.src.login.daos;

import annotations.MyResultSet;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSUsuariosDao extends DaoImpl<UsuarioForm> {

    @Override
    public UsuarioForm make(final ResultSet result) {
        return (UsuarioForm) new MyResultSet(result, UsuarioForm.class).mapToObject();
    }

    @Override
    public void insert(final UsuarioForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.insert_usuario(?, ?, ?)");
        this.setParameter(1, form.getUsername());
        this.setParameter(2, form.getPassword());
        this.setParameter(3, form.getRol().toString());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final UsuarioForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.update_usuario_password(?, ?)");
        this.setParameter(1, form.getUsername());
        this.setParameter(2, form.getPassword());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final UsuarioForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.delete_usuario(?)");
        this.setParameter(1, form.getUsername());
        this.executeUpdate();
        this.disconnect();
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
