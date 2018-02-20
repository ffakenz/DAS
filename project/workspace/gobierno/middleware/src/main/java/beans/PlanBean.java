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

    public PlanBean(Integer id, Integer cuotasPagadas, String vehiculo, String concesionaria, Integer concesionariaId, Long documento, String clientId, Timestamp fechaAlta, Timestamp fechaUltimoUpdate) {
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
	public String toString() {
		return "PlanBean [id=" + id + ", cuotasPagadas=" + cuotasPagadas + ", vehiculo=" + vehiculo + ", concesionaria="
				+ concesionaria + ", concesionariaId=" + concesionariaId + ", documento=" + documento + ", clientId="
				+ clientId + ", fechaAlta=" + fechaAlta + ", fechaUltimoUpdate=" + fechaUltimoUpdate + "]";
	}
}
