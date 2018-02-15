package beans;

import annotations.Column;
import annotations.Entity;

import java.sql.Timestamp;

@Entity
public class PlanBean implements java.io.Serializable {
    // member variables
    @Column(name="planId")
    Integer id;
    @Column(name="cant_cuotas_pagas")
    Integer cuotasPagadas;
    @Column(name="vehiculo")
    String vehiculo;
    @Column(name="concesionaria")
    String concesionaria;
    @Column(name="concesionariaId")
    Integer concesionariaId;
    @Column(name="documento")
    Long documento;
    @Column(name="clientId")
    String clientId;
    @Column(name="fecha_alta")
    Timestamp fechaAlta;
    @Column(name="fecha_ultima_actualizacion")
    Timestamp fechaUltimoUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(Integer cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getConcesionaria() {
        return concesionaria;
    }

    public void setConcesionaria(String concesionaria) {
        this.concesionaria = concesionaria;
    }

    public Integer getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(Integer concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaUltimoUpdate() {
        return fechaUltimoUpdate;
    }

    public void setFechaUltimoUpdate(Timestamp fechaUltimoUpdate) {
        this.fechaUltimoUpdate = fechaUltimoUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanBean)) return false;
        PlanBean plan = (PlanBean) o;
        return this.getId() == plan.getId();
    }

    @Override
    public int hashCode() {
        // Start with a non-zero constant. Prime is preferred
        int result = 17;
        // Include a hash for each field used in equals
        int c = (int)(this.getId() ^ (this.getId() >>> 32));
        return 37 * result + c;
    }

    @Override
    public String toString() {
        return "PlanBean{" +
                "id=" + id +
                ", cuotasPagadas=" + cuotasPagadas +
                ", vehiculo='" + vehiculo + '\'' +
                ", concesionaria='" + concesionaria + '\'' +
                ", concesionariaId=" + concesionariaId +
                ", documento=" + documento +
                ", clientId='" + clientId + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaUltimoUpdate=" + fechaUltimoUpdate +
                '}';
    }
}
