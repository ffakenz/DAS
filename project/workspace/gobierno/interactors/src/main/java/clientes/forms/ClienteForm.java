package clientes.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Date;

public class ClienteForm extends DynaActionForm {
    private Long id;
    private Long documento;
    private String nombre;
    private String apellido;
    private String nro_telefono;
    private String email;
    private Date fecha_de_alta;
    private Long concesionaria;

    @Override
    public String toString() {
        return "RegistrarClienteInteractor{" +
                "id=" + id +
                ", documento=" + documento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nro_telefono='" + nro_telefono + '\'' +
                ", email='" + email + '\'' +
                ", fecha_de_alta=" + fecha_de_alta +
                ", concesionaria=" + concesionaria +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNro_telefono() {
        return nro_telefono;
    }

    public void setNro_telefono(String nro_telefono) {
        this.nro_telefono = nro_telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_de_alta() {
        return fecha_de_alta;
    }

    public void setFecha_de_alta(Date fecha_de_alta) {
        this.fecha_de_alta = fecha_de_alta;
    }

    public Long getConcesionaria() {
        return concesionaria;
    }

    public void setConcesionaria(Long concesionaria) {
        this.concesionaria = concesionaria;
    }
}
