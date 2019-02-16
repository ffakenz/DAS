package ar.edu.ubp.das.src.jobs.consumo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import utils.JsonUtils;

import java.sql.Timestamp;

@Entity
public class ConsumoForm extends DynaActionForm {

    @Column(name = "id")
    private Long id;
    @Column(name = "id_concesionaria")
    private Long idConcesionaria;
    @Column(name = "id_job_consumo")
    private Long idJobConsumo;
    @Column(name = "from")
    private Timestamp from;
    @Column(name = "to")
    private Timestamp to;
    @Column(name = "estado")
    private String estado;
    @Column(name = "description")
    private String description;
    @Column(name = "id_request_resp")
    private String idRequestResp;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(final Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(final Timestamp to) {
        this.to = to;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getIdRequestResp() {
        return idRequestResp;
    }

    public void setIdRequestResp(final String idRequestResp) {
        this.idRequestResp = idRequestResp;
    }

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
