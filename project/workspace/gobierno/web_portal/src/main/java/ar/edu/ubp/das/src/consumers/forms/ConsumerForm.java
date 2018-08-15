package ar.edu.ubp.das.src.consumers.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;

import java.sql.Timestamp;

public class ConsumerForm extends DynaActionForm {
    @Column( name = "id")
    private Long id;
    @Column( name = "documento")
    private Long documento;
    @Column( name = "nombre")
    private String nombre;
    @Column( name = "apellido")
    private String apellido;
    @Column( name = "nro_telefono")
    private String nroTelefono;
    @Column( name = "email")
    private String email;
    @Column( name = "fecha_de_alta")
    private Timestamp fechaAlta;
    @Column( name = "concesionaria")
    private Long concesionaria;


    @Override
    public String toString() {
        return "ConsumerForm{" +
                "id=" + id +
                ", documento=" + documento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nroTelefono='" + nroTelefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", concesionaria=" + concesionaria +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(final Long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(final String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(final Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Long getConcesionaria() {
        return concesionaria;
    }

    public void setConcesionaria(Long concesionaria) {
        this.concesionaria = concesionaria;
    }
}
