package beans;

import annotations.Column;
import annotations.Entity;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

@Entity
public class NotificationUpdate {
    // member variables

    @Column(name = "plan_id")
    @SerializedName("plan_id")
    Long planId;
    @Column(name = "plan_estado")
    @SerializedName("plan_estado")
    String planEstado;
    @Column(name = "plan_fecha_alta")
    @SerializedName("plan_fecha_alta")
    Timestamp planFechaAlta;
    @Column(name = "cuota_nro_cuota")
    @SerializedName("cuota_nro_cuota")
    Long cuotaNroCuota;
    @Column(name = "cuota_fecha_vencimiento")
    @SerializedName("cuota_fecha_vencimiento")
    Timestamp cuotaFechaVencimiento;
    @Column(name = "cuota_monto")
    @SerializedName("cuota_monto")
    Integer cuotaMonto;
    @Column(name = "cuota_fecha_pago")
    @SerializedName("cuota_fecha_pago")
    Timestamp cuotaFechaPago;
    @Column(name = "cuota_fecha_alta")
    @SerializedName("cuota_fecha_alta")
    Timestamp cuotaFechaAlta;
    @Column(name = "cliente_documento")
    @SerializedName("cliente_documento")
    Long clienteDocumento;
    @Column(name = "cliente_nombre")
    @SerializedName("cliente_nombre")
    String clienteNombre;
    @Column(name = "cliente_apellido")
    @SerializedName("cliente_apellido")
    String clienteApellido;
    @Column(name = "cliente_nro_telefono")
    @SerializedName("cliente_nro_telefono")
    String clienteNroTelefono;
    @Column(name = "cliente_email")
    @SerializedName("cliente_email")
    String clienteEmail;
    @Column(name = "vehiculo_id")
    @SerializedName("vehiculo_id")
    Long vehiculoId;
    @Column(name = "vehiculo_tipo")
    @SerializedName("vehiculo_tipo")
    String vehiculoTipo;
    @Column(name = "vehiculo_nombre")
    @SerializedName("vehiculo_nombre")
    String vehiculoNombre;
    @Column(name = "vehiculo_precio")
    @SerializedName("vehiculo_precio")
    Long vehiculoPrecio;
    @Column(name = "vehiculo_marca")
    @SerializedName("vehiculo_marca")
    String vehiculoMarca;
    @Column(name = "vehiculo_modelo")
    @SerializedName("vehiculo_modelo")
    String vehiculoModelo;
    @Column(name = "vehiculo_color")
    @SerializedName("vehiculo_color")
    String vehiculoColor;

    public NotificationUpdate() {
    }

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
