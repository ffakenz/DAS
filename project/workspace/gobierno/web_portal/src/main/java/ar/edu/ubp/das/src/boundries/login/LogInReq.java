package ar.edu.ubp.das.src.boundries.login;

public class LogInReq {
    String usuario;
    String clave;


    public LogInReq(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}