package ar.edu.ubp.das.src.usuarios.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSUsuariosDao extends DaoImpl<UsuarioForm> {

    public MSUsuariosDao() {
        super(UsuarioForm.class);
    }

    @Override
    public void insert(final UsuarioForm form) throws SQLException {
        executeProcedure("dbo.insert_usuario(?, ?, ?, ?)",
                form, "documento","username", "password", "rol");
    }

    @Override
    public void update(final UsuarioForm form) throws SQLException {
        executeProcedure("dbo.update_usuario(?, ?, ?)", form, "documento", "username", "password");
    }

    @Override
    public void delete(final UsuarioForm form) throws SQLException {
        executeProcedure("dbo.delete_usuario(?)", form, "username");
    }

    @Override
    public List<UsuarioForm> select(final UsuarioForm form) throws SQLException {
        return executeQueryProcedure("dbo.get_usuarios", form);
    }

    @Override
    public boolean valid(final UsuarioForm form) throws SQLException {
        // TODO Auto-generated method stub
        return true;
    }

    public Optional<UsuarioForm> selectByUserNameAndPassword(final String username, final String password) throws SQLException {
        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);
        usuarioForm.setPassword(password);

        return this.executeQueryProcedure("dbo.get_usuarios_by_username_password(?, ?)", usuarioForm, "username", "password")
                .stream()
                .findFirst();
    }

    public Optional<UsuarioForm> selectByDocumento(final Long documento) throws SQLException {

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setDocumento(documento);

        return this.executeQueryProcedure("dbo.get_usuario_by_documento(?)",
                usuarioForm, "documento")
                .stream()
                .findFirst();
    }

    public Optional<UsuarioForm> selectByUserName(final String username) throws SQLException {

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);

        return this.executeQueryProcedure("dbo.get_usuarios_by_username(?)",
                usuarioForm, "username")
                .stream()
                .findFirst();
    }

    public void updatePassword(final UsuarioForm form) throws SQLException {
        executeProcedure("dbo.update_usuario_password(?, ?)", form, "documento", "password");
    }

}