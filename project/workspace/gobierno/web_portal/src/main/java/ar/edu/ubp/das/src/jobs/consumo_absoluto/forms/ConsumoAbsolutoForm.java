package ar.edu.ubp.das.src.jobs.consumo_absoluto.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class ConsumoAbsolutoForm extends DynaActionForm {
    @Column(name = "id")
    private Long id;
    @Column(name = "id_sorteo")
    private Long idSorteo;
    @Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "concesionaria_id")
    private Long concesionariaId;
    @Column(name = "plan_id")
    private Long planId;
    @Column(name = "estado_cuenta_id")
    private Long estadoCuentaId;
    @Column(name = "id_request_resp")
    private String idRequestResp;
    @Column(name = "estado")
    private String estado;
    @Column(name = "cause")
    private String cause;

    @Override
    public String toString() {
        return "ConsumoAbsolutoForm{" +
                "id=" + id +
                ", idSorteo=" + idSorteo +
                ", fecha=" + fecha +
                ", concesionariaId=" + concesionariaId +
                ", planId=" + planId +
                ", estadoCuentaId=" + estadoCuentaId +
                ", idRequestResp='" + idRequestResp + '\'' +
                ", estado='" + estado + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }

    public Long getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(Long idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Boolean isForConcesionariaPlan() {
        return this.getPlanId() != null && this.getEstadoCuentaId() != null && this.getIdRequestResp() != null;
    }

    public Boolean isForConcesionaria() {
        return this.getConcesionariaId() != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(final Timestamp fecha) {
        this.fecha = fecha;
    }

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(final Long planId) {
        this.planId = planId;
    }

    public Long getEstadoCuentaId() {
        return estadoCuentaId;
    }

    public void setEstadoCuentaId(final Long estadoCuentaId) {
        this.estadoCuentaId = estadoCuentaId;
    }

    public String getIdRequestResp() {
        return idRequestResp;
    }

    public void setIdRequestResp(final String idRequestResp) {
        this.idRequestResp = idRequestResp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(final String cause) {
        this.cause = cause;
    }
}
