package ar.edu.ubp.das.src.login.model.usuario;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;

import java.sql.SQLException;

public class UsuarioManager {
    private MSUsuariosDaoEx msUsuariosDao;

    public UsuarioManager(final DaoImpl msUsuariosDao) {
        this.msUsuariosDao = new MSUsuariosDaoEx(msUsuariosDao);
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean verifyUsername(final String username) throws SQLException {
        return !msUsuariosDao.selectByUserName(username).isEmpty();
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean verifyUsernameAndPassword(final String username, final String password) throws SQLException {
        return !msUsuariosDao.selectByUserNameAndPassword(username, password).isEmpty();
    }

    public void createUser(final String username, final String password, final UsuarioRol rol) throws SQLException {
        final UsuarioForm form = new UsuarioForm(username, password, rol);
        this.msUsuariosDao.insert(form);
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
