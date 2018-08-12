package ar.edu.ubp.das.src.login.model.usuario;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.DaoExtender;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.List;

public class MSUsuariosDaoEx extends DaoExtender<UsuarioForm> {
    public MSUsuariosDaoEx(final DaoImpl dao) {
        super(dao, UsuarioForm.class);
    }

    public List<UsuarioForm> selectByUserNameAndPassword(final String username, final String password) throws SQLException {
        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);
        usuarioForm.setPassword(password);

        return dao.executeQueryProcedure("dbo.get_usuarios_by_username_password(?, ?)", usuarioForm, "username", "password");
    }

    public List<UsuarioForm> selectByUserName(final String username) throws SQLException {

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);

        return dao.executeQueryProcedure("dbo.get_usuarios_by_username(?)", usuarioForm, "username");
    }
}
