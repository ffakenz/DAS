package beans;

import com.google.gson.annotations.SerializedName;
import utils.JsonUtils;

import java.sql.Timestamp;

public class PlanBean {

    // member variables
    @SerializedName("id")
    Long id;
    @SerializedName("cliente")
    Long clientId;
    @SerializedName("vehiculo")
    Long vehiculo;
    @SerializedName("cant_cuotas_pagas")
    Integer cuotasPagadas;
    String concesionaria;
    Integer concesionariaId;
    Long documento;
    @SerializedName("fecha_alta")
    Timestamp fechaAlta;
    @SerializedName("fecha_ultima_actualizacion")
    Timestamp fechaUltimoUpdate;



    // TODO: List<CuotasBean>


    public PlanBean() {
    }

    public PlanBean(final Long id, final Integer cuotasPagadas, final Long vehiculo, final String concesionaria, final Integer concesionariaId, final Long documento, final Long clientId, final Timestamp fechaAlta, final Timestamp fechaUltimoUpdate) {
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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(final Integer cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public Long getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(final Long vehiculo) {
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(final Long clientId) {
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
        return JsonUtils.toJsonString(this);
    }
}
