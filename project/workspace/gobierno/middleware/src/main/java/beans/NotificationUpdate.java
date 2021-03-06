package beans;

import com.google.gson.annotations.SerializedName;
import utils.JsonUtils;

import java.sql.Timestamp;

public class NotificationUpdate {
    // member variables
    @SerializedName("plan_id")
    private Long planId;
    @SerializedName("plan_estado")
    private String planEstado;
    @SerializedName("plan_fecha_alta")
    private Timestamp planFechaAlta;
    @SerializedName("plan_fecha_ultima_actualizacion")
    private Timestamp planFechaUltimaActualizacion;
    @SerializedName(("plan_tipo_de_plan"))
    private String planTipoDePlan;
    @SerializedName("cuota_nro_cuota")
    private Long cuotaNroCuota;
    @SerializedName("cuota_fecha_vencimiento")
    private Timestamp cuotaFechaVencimiento;
    @SerializedName("cuota_monto")
    private Integer cuotaMonto;
    @SerializedName("cuota_fecha_pago")
    private Timestamp cuotaFechaPago;
    @SerializedName("cuota_fecha_alta")
    private Timestamp cuotaFechaAlta;
    @SerializedName("cliente_documento")
    private Long clienteDocumento;
    @SerializedName("cliente_nombre")
    private String clienteNombre;
    @SerializedName("cliente_apellido")
    private String clienteApellido;
    @SerializedName("cliente_nro_telefono")
    private String clienteNroTelefono;
    @SerializedName("cliente_email")
    private String clienteEmail;
    @SerializedName("vehiculo_id")
    private Long vehiculoId;
    @SerializedName("vehiculo_tipo")
    private String vehiculoTipo;
    @SerializedName("vehiculo_nombre")
    private String vehiculoNombre;
    @SerializedName("vehiculo_precio")
    private Long vehiculoPrecio;
    @SerializedName("vehiculo_marca")
    private String vehiculoMarca;
    @SerializedName("vehiculo_modelo")
    private String vehiculoModelo;
    @SerializedName("vehiculo_color")
    private String vehiculoColor;

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

    public Timestamp getPlanFechaUltimaActualizacion() {
        return planFechaUltimaActualizacion;
    }

    public void setPlanFechaUltimaActualizacion(final Timestamp planFechaUltimaActualizacion) {
        this.planFechaUltimaActualizacion = planFechaUltimaActualizacion;
    }

    public String getPlanTipoDePlan() {
        return planTipoDePlan;
    }

    public void setPlanTipoDePlan(final String planTipoDePlan) {
        this.planTipoDePlan = planTipoDePlan;
    }

    public Long getCuotaNroCuota() {
        return cuotaNroCuota;
    }

    public void setCuotaNroCuota(final Long cuotaNroCuota) {
        this.cuotaNroCuota = cuotaNroCuota;
    }

    public Timestamp getCuotaFechaVencimiento() {
        return cuotaFechaVencimiento;
    }

    public void setCuotaFechaVencimiento(final Timestamp cuotaFechaVencimiento) {
        this.cuotaFechaVencimiento = cuotaFechaVencimiento;
    }

    public Integer getCuotaMonto() {
        return cuotaMonto;
    }

    public void setCuotaMonto(final Integer cuotaMonto) {
        this.cuotaMonto = cuotaMonto;
    }

    public Timestamp getCuotaFechaPago() {
        return cuotaFechaPago;
    }

    public void setCuotaFechaPago(final Timestamp cuotaFechaPago) {
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
