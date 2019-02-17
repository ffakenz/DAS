package ar.edu.ubp.das.src.jobs.consumo.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import com.google.gson.annotations.SerializedName;
import utils.JsonUtils;

import java.sql.Timestamp;

@Entity
public class NotificationUpdateForm extends DynaActionForm {
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
    @Column(name = "plan_tipo_de_plan")
    @SerializedName("plan_tipo_de_plan")
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
    @Column(name = "concesionaria_id")
    private Long concesionariaId;
    @Column(name = "id_job_consumo")
    private Long idJobConsumo;
    @Column(name = "nro_consumo_result")
    private Long nroConsumoResult;

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
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

    public Long getConcesionariaId() {
        return concesionariaId;
    }

    public void setConcesionariaId(final Long concesionariaId) {
        this.concesionariaId = concesionariaId;
    }

    public Long getIdJobConsumo() {
        return idJobConsumo;
    }

    public void setIdJobConsumo(final Long idJobConsumo) {
        this.idJobConsumo = idJobConsumo;
    }

    public Long getNroConsumoResult() {
        return nroConsumoResult;
    }

    public void setNroConsumoResult(final Long nroConsumoResult) {
        this.nroConsumoResult = nroConsumoResult;
    }
}
