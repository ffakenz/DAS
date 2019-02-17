package ar.edu.ubp.das.src.concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import utils.JsonUtils;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class ConcesionariaForm extends DynaActionForm {

    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_registracion")
    private Timestamp fechaRegistracion;
    @Column(name = "fecha_alta")
    private Timestamp fechaAlta;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "tel")
    private String tel;
    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
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

    @Override
    public boolean equals(final Object other) {
        return other instanceof ConcesionariaForm &&
                Objects.equals(this.id, ((ConcesionariaForm) other).id) ||
                Objects.equals(this.cuit, ((ConcesionariaForm) other).cuit);
    }
}
