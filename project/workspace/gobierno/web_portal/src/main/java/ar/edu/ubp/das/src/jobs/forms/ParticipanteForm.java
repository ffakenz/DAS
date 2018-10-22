package ar.edu.ubp.das.src.jobs.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.util.StringJoiner;

@Entity
public class ParticipanteForm extends DynaActionForm {

    @Column(name = "id_sorteo")
    private Long idSorteo;
    @Column(name = "id_plan")
    private Long idPlan;
    @Column(name = "estado")
    private String estado;

    public Long getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(Long idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParticipanteForm.class.getSimpleName() + "[", "]")
                .add("idSorteo=" + idSorteo)
                .add("idPlan=" + idPlan)
                .add("estado='" + estado + "'")
                .toString();
    }
}
