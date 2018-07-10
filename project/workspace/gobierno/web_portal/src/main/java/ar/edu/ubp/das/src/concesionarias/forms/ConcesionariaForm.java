package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Timestamp;

public class ConcesionariaForm extends DynaActionForm {
    private Long id;
    private String nombre;
    private String config;
    private Timestamp fechaRegistracion;
    private Timestamp fechaAlta;
    private String codigo;
    private String direccion;
    private String cuit;
    private String tel;
    private String email;

    @Override
    public String toString() {
        return "ConcesionariaForm{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", config='" + config + '\'' +
                ", fechaRegistracion=" + fechaRegistracion +
                ", fechaAlta=" + fechaAlta +
                ", codigo='" + codigo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cuit='" + cuit + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(final String config) {
        this.config = config;
    }

    public Timestamp getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(final Timestamp fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(final Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(final String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(final String direccion) {
        this.direccion = direccion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(final String cuit) {
        this.cuit = cuit;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(final String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
