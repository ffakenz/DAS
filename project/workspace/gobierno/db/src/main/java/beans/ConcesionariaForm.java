package beans;

import annotations.Column;
import annotations.Entity;
import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Date;

@Entity
public class ConcesionariaForm extends DynaActionForm {
    @Column(name="id")
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="config")
    private String config;
    @Column(name="fechaRegistracion")
    private Date fechaRegistracion;
    @Column(name="fechaAlta")
    private Date fechaAlta;
    @Column(name="codigo")
    private String codigo;
    @Column(name="direccion")
    private String direccion;
    @Column(name="cuit")
    private String cuit;
    @Column(name="tel")
    private String tel;
    @Column(name="email")
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Date getFechaRegistracion() {
        return fechaRegistracion;
    }

    public void setFechaRegistracion(Date fechaRegistracion) {
        this.fechaRegistracion = fechaRegistracion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
