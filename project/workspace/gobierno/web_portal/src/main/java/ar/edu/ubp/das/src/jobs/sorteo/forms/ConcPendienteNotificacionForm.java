package ar.edu.ubp.das.src.jobs.sorteo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import utils.JsonUtils;

@Entity
public class ConcPendienteNotificacionForm extends DynaActionForm {

    @Column(name = "id_sorteo")
    private Long idSorteo;
    @Column(name = "id_concesionaria")
    private Long idConcesionaria;

    public ConcPendienteNotificacionForm(final Long idSorteo, final Long idConcesionaria) {
        this.idSorteo = idSorteo;
        this.idConcesionaria = idConcesionaria;
    }

    public Long getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(final Long idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Long getIdConcesionaria() {
        return idConcesionaria;
    }

    public void setIdConcesionaria(final Long idConcesionaria) {
        this.idConcesionaria = idConcesionaria;
    }

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
