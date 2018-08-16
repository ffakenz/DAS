package ar.edu.ubp.das.src.estado_cuentas.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Timestamp;

public class EstadoCuentaForm extends DynaActionForm {
    private Long id;
    private Long concesionariaId;
    private Long nroPlanConcesionaria;
    private Long documentoConsumer;
    private Long vehiculo;
    private Timestamp fechaAltaConcesionaria;
    private Timestamp fechaAltaSistema;
    private Timestamp fechaUltimaActualizacion;
    private String estado;

    @Override
    public String toString() {
        return "EstadoCuentaForm{" +
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
