package ar.edu.ubp.das.src.estado_cuentas.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class EstadoCuentasForm extends DynaActionForm {
    @Column(name = "id")
    private Long id;
    @Column(name = "concesionaria")
    private Long concesionariaId;
    @Column(name = "nro_plan_concesionaria")
    private Long nroPlanConcesionaria;
    @Column(name = "dni_consumer")
    private Long documentoConsumer;
    @Column(name = "vehiculo")
    private Long vehiculo;
    @Column(name = "fecha_alta_concesionaria")
    private Timestamp fechaAltaConcesionaria;
    @Column(name = "fecha_alta_sistema")
    private Timestamp fechaAltaSistema;
    @Column(name = "fecha_ultima_actualizacion")
    private Timestamp fechaUltimaActualizacion;
    @Column(name = "estado")
    private String estado; // TODO -> create an enum for it

    @Override
    public String toString() {
        return "EstadoCuentasForm{" +
                "id=" + id +
                ", concesionariaId=" + concesionariaId +
                ", nroPlanConcesionaria=" + nroPlanConcesionaria +
                ", documentoConsumer=" + documentoConsumer +
                ", vehiculo=" + vehiculo +
                ", fechaAltaConcesionaria=" + fechaAltaConcesionaria +
                ", fechaAltaSistema=" + fechaAltaSistema +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public Long getNroPlanConcesionaria() {
        return nroPlanConcesionaria;
    }

    public void setNroPlanConcesionaria(final Long nroPlanConcesionaria) {
        this.nroPlanConcesionaria = nroPlanConcesionaria;
    }

    public Long getDocumentoCliente() {
        return documentoConsumer;
    }

    public void setDocumentoCliente(final Long documentoConsumer) {
        this.documentoConsumer = documentoConsumer;
    }

    public Long getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(final Long vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Timestamp getFechaAltaConcesionaria() {
        return fechaAltaConcesionaria;
    }

    public void setFechaAltaConcesionaria(final Timestamp fechaAltaConcesionaria) {
        this.fechaAltaConcesionaria = fechaAltaConcesionaria;
    }

    public Timestamp getFechaAltaSistema() {
        return fechaAltaSistema;
    }

    public void setFechaAltaSistema(final Timestamp fechaAltaSistema) {
        this.fechaAltaSistema = fechaAltaSistema;
    }

    public Timestamp getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(final Timestamp fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }
}
