package ar.edu.ubp.das.src.login.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioRol;

public class UsuarioForm extends DynaActionForm {
    private String username;
    private String password;
    private UsuarioRol rol;

    public UsuarioForm(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public UsuarioForm(final String username, final String password, final UsuarioRol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public UsuarioRol getRol() {
        return rol;
    }

    public void setRol(final UsuarioRol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
