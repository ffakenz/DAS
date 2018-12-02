package beans;

import annotations.Column;
import annotations.Entity;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

@Entity
public class NotificationUpdate {
    // member variables

    @Column(name = "plan_id")
    @SerializedName("planId")
    Long planId;
    @Column(name = "plan_estado")
    @SerializedName("planEstado")
    String planEstado;
    @Column(name = "plan_fecha_alta")
    @SerializedName("planFechaAlta")
    Timestamp planFechaAlta;
    @Column(name = "cuota_nro_cuota")
    @SerializedName("cuotaNroCuota")
    Long cuotaNroCuota;
    @Column(name = "cuota_fecha_vencimiento")
    @SerializedName("cuotaFechaVencimiento")
    Timestamp cuotaFechaVencimiento;
    @Column(name = "cuota_monto")
    @SerializedName("cuotaMonto")
    Integer cuotaMonto;
    @Column(name = "cuota_fecha_pago")
    @SerializedName("cuotaFechaPago")
    Timestamp cuotaFechaPago;
    @Column(name = "cuota_fecha_alta")
    @SerializedName("cuotaFechaAlta")
    Timestamp cuotaFechaAlta;
    @Column(name = "cliente_documento")
    @SerializedName("clienteDocumento")
    Long clienteDocumento;
    @Column(name = "cliente_nombre")
    @SerializedName("clienteNombre")
    String clienteNombre;
    @Column(name = "cliente_apellido")
    @SerializedName("clienteApellido")
    String clienteApellido;
    @Column(name = "cliente_nro_telefono")
    @SerializedName("clienteNroTelefono")
    String clienteNroTelefono;
    @Column(name = "cliente_email")
    @SerializedName("clienteEmail")
    String clienteEmail;
    @Column(name = "vehiculo_id")
    @SerializedName("vehiculoId")
    Long vehiculoId;
    @Column(name = "vehiculo_tipo")
    @SerializedName("vehiculoTipo")
    String vehiculoTipo;
    @Column(name = "vehiculo_nombre")
    @SerializedName("vehiculoNombre")
    String vehiculoNombre;
    @Column(name = "vehiculo_precio")
    @SerializedName("vehiculoPrecio")
    Long vehiculoPrecio;
    @Column(name = "vehiculo_marca")
    @SerializedName("vehiculoMarca")
    String vehiculoMarca;
    @Column(name = "vehiculo_modelo")
    @SerializedName("vehiculoModelo")
    String vehiculoModelo;
    @Column(name = "vehiculo_color")
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
        return "PlanBean{" +
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
