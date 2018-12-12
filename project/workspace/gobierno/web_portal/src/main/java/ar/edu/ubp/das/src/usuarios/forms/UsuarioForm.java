package ar.edu.ubp.das.src.usuarios.forms;


import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

@Entity
public class UsuarioForm extends DynaActionForm {
    @Column(name = "documento")
    private Long documento;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;

    // DB API
    public UsuarioForm() {

    }

    public UsuarioForm(Long documento, String username, String password, String rol) {
        this.documento = documento;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
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
