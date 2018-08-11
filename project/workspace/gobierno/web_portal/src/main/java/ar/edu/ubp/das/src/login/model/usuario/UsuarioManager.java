package ar.edu.ubp.das.src.login.model.usuario;

import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;

public class UsuarioManager {
    private MSUsuariosDao msUsuariosDao;

    public UsuarioManager(final MSUsuariosDao msUsuariosDao) {
        this.msUsuariosDao = msUsuariosDao;
    }
}
