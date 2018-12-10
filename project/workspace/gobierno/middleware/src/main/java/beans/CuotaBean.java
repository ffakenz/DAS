package beans;

import com.google.gson.annotations.SerializedName;
import utils.JsonUtils;

import java.io.Serializable;
import java.sql.Timestamp;

public class CuotaBean implements Serializable {
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

    public static CuotaBean fromNotificationUpdate(final NotificationUpdate notificationUpdate) {
        final CuotaBean bean = new CuotaBean();
        bean.setCuotaNroCuota(notificationUpdate.getCuotaNroCuota());
        bean.setCuotaFechaAlta(notificationUpdate.getCuotaFechaAlta());
        bean.setCuotaFechaVencimiento(notificationUpdate.getCuotaFechaVencimiento());
        bean.setCuotaMonto(notificationUpdate.getCuotaMonto());
        bean.setCuotaFechaPago(notificationUpdate.getCuotaFechaPago());
        return bean;
    }

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
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
}
