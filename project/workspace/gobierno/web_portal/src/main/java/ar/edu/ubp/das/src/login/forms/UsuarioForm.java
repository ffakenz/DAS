package ar.edu.ubp.das.src.login.forms;

import annotations.Column;
import annotations.Entity;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioRol;

@Entity
public class UsuarioForm extends DynaActionForm {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;

    // DB API
    public UsuarioForm() {

    }

    // Client API
    public UsuarioForm(final String username, final String password, final UsuarioRol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol.toString();
    }

    public String getRol() {
        return rol;
    }

    public void setRol(final String rol) {
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
