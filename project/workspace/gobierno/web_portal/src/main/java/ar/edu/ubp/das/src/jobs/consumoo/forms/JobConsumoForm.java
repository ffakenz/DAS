package ar.edu.ubp.das.src.jobs.consumoo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class JobConsumoForm extends DynaActionForm {

    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    private Timestamp fecha;

    @Override
    public String toString() {
        return "JobConsumoForm{" +
                "id=" + id +
                ", fecha=" + fecha +
                '}';
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
}