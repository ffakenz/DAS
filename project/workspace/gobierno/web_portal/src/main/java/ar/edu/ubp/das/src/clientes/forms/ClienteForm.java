package ar.edu.ubp.das.src.clientes.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Timestamp;

public class ClienteForm extends DynaActionForm {
    private Long id;
    private Long documento;
    private String nombre;
    private String apellido;
    private String nroTelefono;
    private String email;
    private Timestamp fechaAlta;
    private Long concesionariaId;

    @Override
    public String toString() {
        return "ClienteForm{" +
                "id=" + id +
                ", documento=" + documento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nroTelefono='" + nroTelefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", concesionariaId=" + concesionariaId +
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

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }
}
