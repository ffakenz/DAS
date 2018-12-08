package ar.edu.ubp.das.src.estado_cuentas.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class CuotasForm extends DynaActionForm {

    @Column(name = "nro_cuota")
    private Long nroCuota;
    @Column(name = "estado_cuenta_id")
    private Long estadoCuentaId;
    @Column(name = "fecha_vencimiento")
    private Timestamp fechaVencimiento;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "fecha_pago")
    private Timestamp fechaPago;
    @Column(name = "fecha_alta_concesionaria")
    private Timestamp fechaAltaConcesionaria;

    @Override
    public String toString() {
        return "CuotasForm{" +
                "nroCuota=" + nroCuota +
                ", estadoCuentaId=" + estadoCuentaId +
                ", fechaVencimiento=" + fechaVencimiento +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                ", fechaAltaConcesionaria=" + fechaAltaConcesionaria +
                '}';
    }

    public Long getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(final Long nroCuota) {
        this.nroCuota = nroCuota;
    }

    public Long getEstadoCuentaId() {
        return estadoCuentaId;
    }

    public void setEstadoCuentaId(final Long estadoCuentaId) {
        this.estadoCuentaId = estadoCuentaId;
    }

    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(final Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(final Integer monto) {
        this.monto = monto;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(final Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Timestamp getFechaAltaConcesionaria() {
        return fechaAltaConcesionaria;
    }

    public void setFechaAltaConcesionaria(final Timestamp fechaAltaConcesionaria) {
        this.fechaAltaConcesionaria = fechaAltaConcesionaria;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CuotasForm that = (CuotasForm) o;
        return Objects.equals(nroCuota, that.nroCuota) &&
                Objects.equals(estadoCuentaId, that.estadoCuentaId) &&
                Objects.equals(fechaVencimiento, that.fechaVencimiento) &&
                Objects.equals(monto, that.monto) &&
                Objects.equals(fechaPago, that.fechaPago) &&
                Objects.equals(fechaAltaConcesionaria, that.fechaAltaConcesionaria);
    }
}
