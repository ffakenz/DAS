package ar.edu.ubp.das.src.login.model.usuario;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;

public class UsuarioManager {

    private MSUsuariosDao msUsuariosDao;

    public UsuarioManager(final DaoImpl msUsuariosDao) {
        this.msUsuariosDao = (MSUsuariosDao) msUsuariosDao;
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean verifyUsername(final String username) throws SQLException {
        return !msUsuariosDao.selectByUserName(username).isEmpty();
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean verifyUsernameAndPassword(final String username, final String password) throws SQLException {
        return !msUsuariosDao.selectByUserNameAndPassword(username, password).isEmpty();
    }

    public void createUser(final UsuarioForm usuarioForm) throws SQLException {
        this.msUsuariosDao.insert(usuarioForm);
    }

    public void deleteUser(final String username) throws SQLException {
        final UsuarioForm form = new UsuarioForm();
        form.setUsername(username);
        this.msUsuariosDao.delete(form);
    }

    public void updatePassword(final String username, final String newPassword) throws SQLException {
        final UsuarioForm form = new UsuarioForm();
        form.setUsername(username);
        form.setPassword(newPassword);
        this.msUsuariosDao.update(form);
    }
}
