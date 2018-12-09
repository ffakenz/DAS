package ar.edu.ubp.das.src.jobs.consumoo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class ConsumoForm extends DynaActionForm {

    @Column(name = "id_concesionaria")
    private Long idConcesionaria;
    @Column(name = "id_job_consumo")
    private Long idJobConsumo;
    @Column(name = "offset")
    private Timestamp offset;
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_request_resp")
    private String idRequestResp;

    @Override
    public String toString() {
        return "ConsumoForm{" +
                "idConcesionaria=" + idConcesionaria +
                ", idJobConsumo=" + idJobConsumo +
                ", offset=" + offset +
                ", estado='" + estado + '\'' +
                ", idRequestResp='" + idRequestResp + '\'' +
                '}';
    }

    public Long getIdConcesionaria() {
        return idConcesionaria;
    }

    public void setIdConcesionaria(final Long idConcesionaria) {
        this.idConcesionaria = idConcesionaria;
    }

    public Long getIdJobConsumo() {
        return idJobConsumo;
    }

    public void setIdJobConsumo(final Long idJobConsumo) {
        this.idJobConsumo = idJobConsumo;
    }

    public Timestamp getOffset() {
        return offset;
    }

    public void setOffset(final Timestamp offset) {
        this.offset = offset;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getIdRequestResp() {
        return idRequestResp;
    }

    public void setIdRequestResp(final String idRequestResp) {
        this.idRequestResp = idRequestResp;
    }
}