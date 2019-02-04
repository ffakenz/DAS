package ar.edu.ubp.das.src.jobs.sorteo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class EjecucionesSorteoForm extends DynaActionForm {

    @Column(name = "id")
    private Long id;
    @Column(name = "id_sorteo")
    private Long idSorteo;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha")
    private Timestamp fecha;
    @Column(name = "comments")
    private String comments;

    public void setEstado(final EstadoSorteo estado) {
        this.estado = estado.getTipo();
    }

    public EstadoSorteo getEstadoSorteo() {
        return EstadoSorteo.valueOf(this.estado);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public Long getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(Long idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "EjecucionesSorteoForm{" +
                "id=" + id +
                ", idSorteo=" + idSorteo +
                ", estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", comments='" + comments + '\'' +
                '}';
    }
}
