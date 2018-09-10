package ar.edu.ubp.das.src.usuarios.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.List;

public class MSUsuariosDao extends DaoImpl<UsuarioForm> {

    public MSUsuariosDao() {
        super(UsuarioForm.class);
    }

    @Override
    public void insert(final UsuarioForm form) throws SQLException {
        executeProcedure("dbo.insert_usuario(?, ?, ?)", form, "username", "password", "rol");
    }

    @Override
    public void update(final UsuarioForm form) throws SQLException {
        executeProcedure("dbo.update_usuario_password(?, ?)", form, "username", "password");
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

    public List<UsuarioForm> selectByUserNameAndPassword(final String username, final String password) throws SQLException {
        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);
        usuarioForm.setPassword(password);

        return this.executeQueryProcedure("dbo.get_usuarios_by_username_password(?, ?)", usuarioForm, "username", "password");
    }

    public List<UsuarioForm> selectByUserName(final String username) throws SQLException {

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);

        return this.executeQueryProcedure("dbo.get_usuarios_by_username(?)", usuarioForm, "username");
    }

}


/*
    TODO: Registrar Usuario Consumer
    dni = parameter.get('dni')
    Option<consumer> = getConsumerByDNI(dni)

    if(!consumer.isPresent){
        throw Exception("No consumer exists for with dni ${dni}")
    } else if (consumer.get.username.isPresent) {
        throw Exception("The consumer already have a user")
    } else {
        username = parameter.get('username')
        username = parameter.get('password')
        createUsuario(username, 'consumer')
    }
*/