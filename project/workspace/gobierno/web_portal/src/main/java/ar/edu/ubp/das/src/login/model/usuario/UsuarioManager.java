package ar.edu.ubp.das.src.login.model.usuario;

import ar.edu.ubp.das.mvc.db.DaoImpl;

import java.sql.SQLException;

public class UsuarioManager {
    private MSUsuariosDaoEx msUsuariosDao;

    public UsuarioManager(final DaoImpl msUsuariosDao) {
        this.msUsuariosDao = new MSUsuariosDaoEx(msUsuariosDao);
    }

    // is there any usuario in the repository such that is equals to the one sent by parameter ?
    public Boolean verifyUsernameAndPassword(final String username, final String password) throws SQLException {
        return !msUsuariosDao.selectByUserNameAndPassword(username, password).isEmpty();
    }
}
