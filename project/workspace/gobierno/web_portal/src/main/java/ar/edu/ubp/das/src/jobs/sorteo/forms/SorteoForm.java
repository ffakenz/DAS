package ar.edu.ubp.das.src.jobs.sorteo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.util.Date;

@Entity
public class SorteoForm extends DynaActionForm {

    @Column(name = "id")
    private Long id;
    @Column(name = "mes_sorteo")
    private Integer mesSorteo;
    @Column(name = "anio_sorteo")
    private Integer anioSorteo;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_ejecucion")
    private Date fechaEjecucion;

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

    public Integer getAnioSorteo() {
        return anioSorteo;
    }

    public void setAnioSorteo(Integer anioSorteo) {
        this.anioSorteo = anioSorteo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(final Date fecha) {
        this.fechaEjecucion = fecha;
    }

    @Override
    public String toString() {
        return "SorteoForm{" +
                "id=" + id +
                ", mesSorteo=" + mesSorteo +
                ", anioSorteo=" + anioSorteo +
                ", estado='" + estado + '\'' +
                ", fechaEjecucion=" + fechaEjecucion +
                '}';
    }
}
