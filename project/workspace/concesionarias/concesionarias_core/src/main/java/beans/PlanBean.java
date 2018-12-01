package beans;

import annotations.Column;
import annotations.Entity;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

@Entity
public class PlanBean {
    // member variables

    @Column(name = "id")
    @SerializedName("id")
    Long id;
    @Column(name = "cliente")
    @SerializedName("cliente")
    Long clienteId;
    @Column(name = "vehiculo")
    @SerializedName("vehiculo")
    Long vehiculoId;
    @Column(name = "tipo_de_plan")
    @SerializedName("tipo_de_plan")
    String tipoDePlan;
    @Column(name = "cant_cuotas_pagas")
    @SerializedName("cant_cuotas_pagas")
    Integer cantCuotasPagas;
    @Column(name = "nombre")
    @SerializedName("nombre")
    String nombre;
    @Column(name = "fecha_alta")
    @SerializedName("fecha_alta")
    Timestamp fechaAlta;
    @Column(name = "fecha_ultima_actualizacion")
    @SerializedName("fecha_ultima_actualizacion")
    Timestamp fechaUltimaActualizacion;

    @Override
    public String toString() {
        return "PlanBean{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", vehiculoId=" + vehiculoId +
                ", tipoDePlan='" + tipoDePlan + '\'' +
                ", cantCuotasPagas=" + cantCuotasPagas +
                ", nombre='" + nombre + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaUltimaActualizacion=" + fechaUltimaActualizacion +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(final Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(final Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getTipoDePlan() {
        return tipoDePlan;
    }

    public void setTipoDePlan(final String tipoDePlan) {
        this.tipoDePlan = tipoDePlan;
    }

    public Integer getCantCuotasPagas() {
        return cantCuotasPagas;
    }

    public void setCantCuotasPagas(final Integer cantCuotasPagas) {
        this.cantCuotasPagas = cantCuotasPagas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(final Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(final Timestamp fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }
}
