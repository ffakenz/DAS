package ar.edu.ubp.das.src.login.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class UsuarioForm extends DynaActionForm {
    private String tipo;
    private String username;
    private String password;

    public UsuarioForm() {}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
