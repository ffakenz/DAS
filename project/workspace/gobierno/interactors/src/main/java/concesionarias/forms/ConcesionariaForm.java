package concesionarias.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Date;

public class ConcesionariaForm extends DynaActionForm {
    private Long id;
    private String nombre;
    private String config;
    private Date fechaRegistracion;
    private Date fechaAlta;
    private String codigo;

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

    @Override
    public String toString() {
        return "ConcesionariaForm{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", config='" + config + '\'' +
                ", fechaRegistracion=" + fechaRegistracion +
                ", fechaAlta=" + fechaAlta +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
