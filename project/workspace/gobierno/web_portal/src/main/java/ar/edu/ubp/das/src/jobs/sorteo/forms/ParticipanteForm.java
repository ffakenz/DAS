package ar.edu.ubp.das.src.jobs.sorteo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

@Entity
public class ParticipanteForm extends DynaActionForm {

    @Column(name = "id_sorteo")
    private Long idSorteo;
    @Column(name = "id_plan")
    private Long idPlan;
    @Column(name = "id_concesionaria")
    private Long idConcesionaria;
    @Column(name = "id_consumer")
    private Long idConsumer;
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    @Column(name = "estado")
    private String estado;

    @Override
    public String toString() {
        return "ParticipanteForm{" +
                "idSorteo=" + idSorteo +
                ", idPlan=" + idPlan +
                ", idConcesionaria=" + idConcesionaria +
                ", idConsumer=" + idConsumer +
                ", idVehiculo=" + idVehiculo +
                ", estado='" + estado + '\'' +
                '}';
    }

    public Long getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(final Long idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(final Long idPlan) {
        this.idPlan = idPlan;
    }

    public Long getIdConcesionaria() {
        return idConcesionaria;
    }

    public void setIdConcesionaria(final Long idConcesionaria) {
        this.idConcesionaria = idConcesionaria;
    }

    public Long getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(final Long idConsumer) {
        this.idConsumer = idConsumer;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(final Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public void setEstado(final EstadoParticipante estado) {
        this.estado = estado.getTipo();
    }

    public EstadoParticipante getEstadoParticipante() {
        return EstadoParticipante.valueOf(this.estado);
    }

}
