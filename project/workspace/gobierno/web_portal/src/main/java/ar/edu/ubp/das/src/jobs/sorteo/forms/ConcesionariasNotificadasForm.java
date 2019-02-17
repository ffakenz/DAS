package ar.edu.ubp.das.src.jobs.sorteo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

@Entity
public class ConcesionariasNotificadasForm extends DynaActionForm {

    @Column(name = "id_sorteo")
    private Long idSorteo;
    @Column(name = "id_concesionaria")
    private Long idConcesionaria;

    public Long getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(Long idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Long getIdConcesionaria() {
        return idConcesionaria;
    }

    public void setIdConcesionaria(Long idConcesionaria) {
        this.idConcesionaria = idConcesionaria;
    }

    @Override
    public String toString() {
        return "ConcesionariasNotificadasForm{" +
                "idSorteo=" + idSorteo +
                ", idConcesionaria=" + idConcesionaria +
                '}';
    }
}
