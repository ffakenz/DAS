package beans;

import annotations.Column;
import annotations.Entity;

import java.sql.Timestamp;

@Entity
public class EstadoCuentaForm implements DynaActionForm {
    @Column(name="id")
    private Long id;
    @Column(name="concesionaria")
    private Long concesionariaId;
    @Column(name="nro_plan_concesionaria")
    private Long nroPlanConcesionaria;
    @Column(name="documento_cliente")
    private Long documentoCliente;
    @Column(name="vehiculo")
    private Long vehiculo;
    @Column(name="fecha_alta_concesionaria")
    private Timestamp fechaAltaConcesionaria;
    @Column(name="fecha_alta_sistema")
    private Timestamp fechaAltaSistema;
    @Column(name="fecha_ultima_actualizacion")
    private Timestamp fechaUltimaActualizacion;

    @Override
    public String toString() {
        return "EstadoCuentaForm{" +
                "id=" + id +
                ", concesionariaId=" + concesionariaId +
                ", nroPlanConcesionaria=" + nroPlanConcesionaria +
                ", documentoCliente=" + documentoCliente +
                ", vehiculo=" + vehiculo +
                ", fechaAltaConcesionaria=" + fechaAltaConcesionaria +
                ", fechaAltaSistema=" + fechaAltaSistema +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public Long getNroPlanConcesionaria() {
        return nroPlanConcesionaria;
    }

    public void setNroPlanConcesionaria(Long nroPlanConcesionaria) {
        this.nroPlanConcesionaria = nroPlanConcesionaria;
    }

    public Long getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(Long documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public Long getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Long vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Timestamp getFechaAltaConcesionaria() {
        return fechaAltaConcesionaria;
    }

    public void setFechaAltaConcesionaria(Timestamp fechaAltaConcesionaria) {
        this.fechaAltaConcesionaria = fechaAltaConcesionaria;
    }

    public Timestamp getFechaAltaSistema() {
        return fechaAltaSistema;
    }

    public void setFechaAltaSistema(Timestamp fechaAltaSistema) {
        this.fechaAltaSistema = fechaAltaSistema;
    }

    public Timestamp getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Timestamp fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }
}
