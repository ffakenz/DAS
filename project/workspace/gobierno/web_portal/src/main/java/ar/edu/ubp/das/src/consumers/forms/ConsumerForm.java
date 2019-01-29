package ar.edu.ubp.das.src.consumers.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class ConsumerForm extends DynaActionForm {

    @Column(name = "documento")
    private Long documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "nro_telefono")
    private String nroTelefono;
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_de_alta") // remove setter
    private Timestamp fechaAlta;


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

    @Override
    public String toString() {
        return new StringJoiner(", ", ConsumerForm.class.getSimpleName() + "[", "]")
                .add("documento=" + documento)
                .add("nombre='" + nombre + "'")
                .add("apellido='" + apellido + "'")
                .add("nroTelefono='" + nroTelefono + "'")
                .add("email='" + email + "'")
                .add("fechaAlta=" + fechaAlta)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ConsumerForm that = (ConsumerForm) o;
        return Objects.equals(documento, that.documento);
    }
}
