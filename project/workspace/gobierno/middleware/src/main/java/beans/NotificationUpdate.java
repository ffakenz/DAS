package beans;

import com.google.gson.annotations.SerializedName;
import utils.JsonUtils;

import java.sql.Timestamp;

public class NotificationUpdate {
    // member variables
    @SerializedName("plan_id")
    Long planId;
    @SerializedName("plan_estado")
    String planEstado;
    @SerializedName("plan_fecha_alta")
    Timestamp planFechaAlta;
    @SerializedName("cuota_nro_cuota")
    Long cuotaNroCuota;
    @SerializedName("cuota_fecha_vencimiento")
    Timestamp cuotaFechaVencimiento;
    @SerializedName("cuota_monto")
    Integer cuotaMonto;
    @SerializedName("cuota_fecha_pago")
    Timestamp cuotaFechaPago;
    @SerializedName("cuota_fecha_alta")
    Timestamp cuotaFechaAlta;
    @SerializedName("cliente_documento")
    Long clienteDocumento;
    @SerializedName("cliente_nombre")
    String clienteNombre;
    @SerializedName("cliente_apellido")
    String clienteApellido;
    @SerializedName("cliente_nro_telefono")
    String clienteNroTelefono;
    @SerializedName("cliente_email")
    String clienteEmail;
    @SerializedName("vehiculo_id")
    Long vehiculoId;
    @SerializedName("vehiculo_tipo")
    String vehiculoTipo;
    @SerializedName("vehiculo_nombre")
    String vehiculoNombre;
    @SerializedName("vehiculo_precio")
    Long vehiculoPrecio;
    @SerializedName("vehiculo_marca")
    String vehiculoMarca;
    @SerializedName("vehiculo_modelo")
    String vehiculoModelo;
    @SerializedName("vehiculo_color")
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
        return JsonUtils.toJsonString(this);
    }
}
