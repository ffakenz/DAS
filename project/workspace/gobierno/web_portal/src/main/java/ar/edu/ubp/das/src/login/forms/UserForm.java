package ar.edu.ubp.das.src.login.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class UserForm extends DynaActionForm{
    private String nombre;
    private String password;

    public UserForm() {}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
