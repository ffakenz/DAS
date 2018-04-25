package estado_cuentas.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Timestamp;

public class EstadoCuentaForm extends DynaActionForm {
    private Long id;
    private Long concesionariaId;
    private Long nroPlanConcesionaria;
    private Long documentoCliente;
    private Long vehiculoId;
    private Timestamp fechaAltaConcesionaria;
    private Timestamp fechaAltaSistema;
    private Timestamp fechaUltimaActualizacion;

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

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
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

    @Override
    public String toString() {
        return "EstadoCuentaForm{" +
                "id=" + id +
                ", concesionariaId=" + concesionariaId +
                ", nroPlanConcesionaria=" + nroPlanConcesionaria +
                ", documentoCliente=" + documentoCliente +
                ", vehiculoId=" + vehiculoId +
                ", fechaAltaConcesionaria=" + fechaAltaConcesionaria +
                ", fechaAltaSistema=" + fechaAltaSistema +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                '}';
    }
}
