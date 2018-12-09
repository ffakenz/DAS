package beans;

import annotations.Column;
import annotations.Entity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class NotificationUpdate implements Serializable {
    // member variables

    @Column(name = "plan_id")
    @SerializedName("plan_id")
    private Long planId;
    @Column(name = "plan_estado")
    @SerializedName("plan_estado")
    private String planEstado;
    @Column(name = "plan_fecha_alta")
    @SerializedName("plan_fecha_alta")
    private Timestamp planFechaAlta;
    @Column(name = "plan_fecha_ultima_actualizacion")
    @SerializedName("plan_fecha_ultima_actualizacion")
    private Timestamp planFechaUltimaActualizacion;
    @Column(name ="plan_tipo_de_plan")
    @SerializedName(("plan_tipo_de_plan"))
    private String planTipoDePlan;
    @Column(name = "cuota_nro_cuota")
    @SerializedName("cuota_nro_cuota")
    private Long cuotaNroCuota;
    @Column(name = "cuota_fecha_vencimiento")
    @SerializedName("cuota_fecha_vencimiento")
    private Timestamp cuotaFechaVencimiento;
    @Column(name = "cuota_monto")
    @SerializedName("cuota_monto")
    private Integer cuotaMonto;
    @Column(name = "cuota_fecha_pago")
    @SerializedName("cuota_fecha_pago")
    private Timestamp cuotaFechaPago;
    @Column(name = "cuota_fecha_alta")
    @SerializedName("cuota_fecha_alta")
    private Timestamp cuotaFechaAlta;
    @Column(name = "cliente_documento")
    @SerializedName("cliente_documento")
    private Long clienteDocumento;
    @Column(name = "cliente_nombre")
    @SerializedName("cliente_nombre")
    private String clienteNombre;
    @Column(name = "cliente_apellido")
    @SerializedName("cliente_apellido")
    private String clienteApellido;
    @Column(name = "cliente_nro_telefono")
    @SerializedName("cliente_nro_telefono")
    private String clienteNroTelefono;
    @Column(name = "cliente_email")
    @SerializedName("cliente_email")
    private String clienteEmail;
    @Column(name = "vehiculo_id")
    @SerializedName("vehiculo_id")
    private Long vehiculoId;
    @Column(name = "vehiculo_tipo")
    @SerializedName("vehiculo_tipo")
    private String vehiculoTipo;
    @Column(name = "vehiculo_nombre")
    @SerializedName("vehiculo_nombre")
    private String vehiculoNombre;
    @Column(name = "vehiculo_precio")
    @SerializedName("vehiculo_precio")
    private Long vehiculoPrecio;
    @Column(name = "vehiculo_marca")
    @SerializedName("vehiculo_marca")
    private String vehiculoMarca;
    @Column(name = "vehiculo_modelo")
    @SerializedName("vehiculo_modelo")
    private String vehiculoModelo;
    @Column(name = "vehiculo_color")
    @SerializedName("vehiculo_color")
    private String vehiculoColor;

    public NotificationUpdate() {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NotificationUpdate that = (NotificationUpdate) o;
        return Objects.equals(planId, that.planId) &&
                Objects.equals(planEstado, that.planEstado) &&
                Objects.equals(planFechaAlta, that.planFechaAlta) &&
                Objects.equals(planTipoDePlan, that.planTipoDePlan) &&
                Objects.equals(cuotaNroCuota, that.cuotaNroCuota) &&
                Objects.equals(cuotaFechaVencimiento, that.cuotaFechaVencimiento) &&
                Objects.equals(cuotaMonto, that.cuotaMonto) &&
                Objects.equals(cuotaFechaPago, that.cuotaFechaPago) &&
                Objects.equals(cuotaFechaAlta, that.cuotaFechaAlta) &&
                Objects.equals(clienteDocumento, that.clienteDocumento) &&
                Objects.equals(clienteNombre, that.clienteNombre) &&
                Objects.equals(clienteApellido, that.clienteApellido) &&
                Objects.equals(clienteNroTelefono, that.clienteNroTelefono) &&
                Objects.equals(clienteEmail, that.clienteEmail) &&
                Objects.equals(vehiculoId, that.vehiculoId) &&
                Objects.equals(vehiculoTipo, that.vehiculoTipo) &&
                Objects.equals(vehiculoNombre, that.vehiculoNombre) &&
                Objects.equals(vehiculoPrecio, that.vehiculoPrecio) &&
                Objects.equals(vehiculoMarca, that.vehiculoMarca) &&
                Objects.equals(vehiculoModelo, that.vehiculoModelo) &&
                Objects.equals(vehiculoColor, that.vehiculoColor);
    }
}
