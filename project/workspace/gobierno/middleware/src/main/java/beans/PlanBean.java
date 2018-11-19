package beans;

import java.sql.Timestamp;

public class PlanBean {

    // member variables
    Integer id;
    Integer cuotasPagadas;
    String vehiculo;
    String concesionaria;
    Integer concesionariaId;
    Long documento;
    String clientId;
    Timestamp fechaAlta;
    Timestamp fechaUltimoUpdate;
    // TODO: List<CuotasBean>

    public PlanBean() {
    }

    public PlanBean(final Integer id, final Integer cuotasPagadas, final String vehiculo, final String concesionaria, final Integer concesionariaId, final Long documento, final String clientId, final Timestamp fechaAlta, final Timestamp fechaUltimoUpdate) {
        this.id = id;
        this.cuotasPagadas = cuotasPagadas;
        this.vehiculo = vehiculo;
        this.concesionaria = concesionaria;
        this.concesionariaId = concesionariaId;
        this.documento = documento;
        this.clientId = clientId;
        this.fechaAlta = fechaAlta;
        this.fechaUltimoUpdate = fechaUltimoUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(final Integer cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(final String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getConcesionaria() {
        return concesionaria;
    }

    public void setConcesionaria(final String concesionaria) {
        this.concesionaria = concesionaria;
    }

    public Integer getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Integer concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(final Long documento) {
        this.documento = documento;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(final Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaUltimoUpdate() {
        return fechaUltimoUpdate;
    }

    public void setFechaUltimoUpdate(final Timestamp fechaUltimoUpdate) {
        this.fechaUltimoUpdate = fechaUltimoUpdate;
    }

    @Override
    public String toString() {
        return "PlanBean [id=" + id + ", cuotasPagadas=" + cuotasPagadas + ", vehiculo=" + vehiculo + ", concesionaria="
                + concesionaria + ", concesionariaId=" + concesionariaId + ", documento=" + documento + ", clientId="
                + clientId + ", fechaAlta=" + fechaAlta + ", fechaUltimoUpdate=" + fechaUltimoUpdate + "]";
    }
}
