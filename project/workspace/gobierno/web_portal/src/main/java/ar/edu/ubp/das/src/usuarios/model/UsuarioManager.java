package ar.edu.ubp.das.src.usuarios.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.Manager;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;

import java.sql.SQLException;
import java.util.Optional;

public class UsuarioManager extends Manager<MSUsuariosDao> {


    public UsuarioManager(final DaoImpl dao) {
        super(dao);
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean verifyUsername(final String username) throws SQLException {
        return !dao.selectByUserName(username).isEmpty();
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Optional<UsuarioForm> verifyUsernameAndPassword(final String username, final String password) throws SQLException {
        return dao.selectByUserNameAndPassword(username, password);
    }

    public void createUser(final UsuarioForm usuarioForm) throws SQLException {
        this.dao.insert(usuarioForm);
    }

    public void deleteUser(final String username) throws SQLException {
        final UsuarioForm form = new UsuarioForm();
        form.setUsername(username);
        this.dao.delete(form);
    }

    public void updatePassword(final String username, final String newPassword) throws SQLException {
        final UsuarioForm form = new UsuarioForm();
        form.setUsername(username);
        form.setPassword(newPassword);
        this.dao.update(form);
    }
}
