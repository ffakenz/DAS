package ar.edu.ubp.das.src.jobs.sorteo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;
import java.util.StringJoiner;

@Entity
public class SorteoForm extends DynaActionForm {

    @Column(name = "id")
    private Long id;
    @Column(name = "mes_sorteo")
    private Integer mesSorteo;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_ejecucion")
    private Timestamp fechaEjecucion;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getMesSorteo() {
        return mesSorteo;
    }

    public void setMesSorteo(final Integer mesSorteo) {
        this.mesSorteo = mesSorteo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public Timestamp getFecha() {
        return fechaEjecucion;
    }

    public void setFecha(final Timestamp fecha) {
        this.fechaEjecucion = fecha;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SorteoForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("mesSorteo=" + mesSorteo)
                .add("estado='" + estado + "'")
                .add("fecha_ejecucion=" + fechaEjecucion)
                .toString();
    }
}
