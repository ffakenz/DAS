package beans;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class NotificationUpdate {
    // member variables
    @SerializedName("planId")
    Long planId;
    @SerializedName("planEstado")
    String planEstado;
    @SerializedName("planFechaAlta")
    Timestamp planFechaAlta;
    @SerializedName("cuotaNroCuota")
    Long cuotaNroCuota;
    @SerializedName("cuotaFechaVencimiento")
    Timestamp cuotaFechaVencimiento;
    @SerializedName("cuotaMonto")
    Integer cuotaMonto;
    @SerializedName("cuotaFechaPago")
    Timestamp cuotaFechaPago;
    @SerializedName("cuotaFechaAlta")
    Timestamp cuotaFechaAlta;
    @SerializedName("clienteDocumento")
    Long clienteDocumento;
    @SerializedName("clienteNombre")
    String clienteNombre;
    @SerializedName("clienteApellido")
    String clienteApellido;
    @SerializedName("clienteNroTelefono")
    String clienteNroTelefono;
    @SerializedName("clienteEmail")
    String clienteEmail;
    @SerializedName("vehiculoId")
    Long vehiculoId;
    @SerializedName("vehiculoTipo")
    String vehiculoTipo;
    @SerializedName("vehiculoNombre")
    String vehiculoNombre;
    @SerializedName("vehiculoPrecio")
    Long vehiculoPrecio;
    @SerializedName("vehiculoMarca")
    String vehiculoMarca;
    @SerializedName("vehiculoModelo")
    String vehiculoModelo;
    @SerializedName("vehiculoColor")
    String vehiculoColor;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(final Long planId) {
        this.planId = planId;
    }

    public String getPlanEstado() {
        return planEstado;
    }

    public void setPlanEstado(final String planEstado) {
        this.planEstado = planEstado;
    }

    public Timestamp getPlanFechaAlta() {
        return planFechaAlta;
    }

    public void setPlanFechaAlta(final Timestamp planFechaAlta) {
        this.planFechaAlta = planFechaAlta;
    }

    public Long getCoutaNroCuota() {
        return cuotaNroCuota;
    }

    public void setCoutaNroCuota(final Long cuotaNroCuota) {
        this.cuotaNroCuota = cuotaNroCuota;
    }

    public Timestamp getCoutaFechaVencimiento() {
        return cuotaFechaVencimiento;
    }

    public void setCoutaFechaVencimiento(final Timestamp cuotaFechaVencimiento) {
        this.cuotaFechaVencimiento = cuotaFechaVencimiento;
    }

    public Integer getCoutaMonto() {
        return cuotaMonto;
    }

    public void setCoutaMonto(final Integer cuotaMonto) {
        this.cuotaMonto = cuotaMonto;
    }

    public Timestamp getCoutaFechaPago() {
        return cuotaFechaPago;
    }

    public void setCoutaFechaPago(final Timestamp cuotaFechaPago) {
        this.cuotaFechaPago = cuotaFechaPago;
    }

    public Timestamp getCuotaFechaAlta() {
        return cuotaFechaAlta;
    }

    public void setCuotaFechaAlta(final Timestamp cuotaFechaAlta) {
        this.cuotaFechaAlta = cuotaFechaAlta;
    }

    public Long getClienteDocumento() {
        return clienteDocumento;
    }

    public void setClienteDocumento(final Long clienteDocumento) {
        this.clienteDocumento = clienteDocumento;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(final String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(final String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }

    public String getClienteNroTelefono() {
        return clienteNroTelefono;
    }

    public void setClienteNroTelefono(final String clienteNroTelefono) {
        this.clienteNroTelefono = clienteNroTelefono;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(final String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(final Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getVehiculoTipo() {
        return vehiculoTipo;
    }

    public void setVehiculoTipo(final String vehiculoTipo) {
        this.vehiculoTipo = vehiculoTipo;
    }

    public String getVehiculoNombre() {
        return vehiculoNombre;
    }

    public void setVehiculoNombre(final String vehiculoNombre) {
        this.vehiculoNombre = vehiculoNombre;
    }

    public Long getVehiculoPrecio() {
        return vehiculoPrecio;
    }

    public void setVehiculoPrecio(final Long vehiculoPrecio) {
        this.vehiculoPrecio = vehiculoPrecio;
    }

    public String getVehiculoMarca() {
        return vehiculoMarca;
    }

    public void setVehiculoMarca(final String vehiculoMarca) {
        this.vehiculoMarca = vehiculoMarca;
    }

    public String getVehiculoModelo() {
        return vehiculoModelo;
    }

    public void setVehiculoModelo(final String vehiculoModelo) {
        this.vehiculoModelo = vehiculoModelo;
    }

    public String getVehiculoColor() {
        return vehiculoColor;
    }

    public void setVehiculoColor(final String vehiculoColor) {
        this.vehiculoColor = vehiculoColor;
    }

    @Override
    public String toString() {
        return "NotificationUpdate{" +
                "planId=" + planId +
                ", planEstado='" + planEstado + '\'' +
                ", planFechaAlta=" + planFechaAlta +
                ", cuotaNroCuota=" + cuotaNroCuota +
                ", cuotaFechaVencimiento=" + cuotaFechaVencimiento +
                ", cuotaMonto=" + cuotaMonto +
                ", cuotaFechaPago=" + cuotaFechaPago +
                ", cuotaFechaAlta=" + cuotaFechaAlta +
                ", clienteDocumento=" + clienteDocumento +
                ", clienteNombre='" + clienteNombre + '\'' +
                ", clienteApellido='" + clienteApellido + '\'' +
                ", clienteNroTelefono='" + clienteNroTelefono + '\'' +
                ", clienteEmail='" + clienteEmail + '\'' +
                ", vehiculoId=" + vehiculoId +
                ", vehiculoTipo='" + vehiculoTipo + '\'' +
                ", vehiculoNombre='" + vehiculoNombre + '\'' +
                ", vehiculoPrecio=" + vehiculoPrecio +
                ", vehiculoMarca='" + vehiculoMarca + '\'' +
                ", vehiculoModelo='" + vehiculoModelo + '\'' +
                ", vehiculoColor='" + vehiculoColor + '\'' +
                '}';
    }
}
