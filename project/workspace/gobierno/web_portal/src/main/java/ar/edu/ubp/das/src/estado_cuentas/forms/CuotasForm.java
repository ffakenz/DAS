package ar.edu.ubp.das.src.estado_cuentas.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;

import java.sql.Timestamp;

@Entity
public class CuotasForm extends DynaActionForm {

    @Column(name = "estado_cuenta_id")
    private Long estadoCuentaId;
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha_vencimiento")
    private Timestamp fechaVencimiento;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "fecha_pago")
    private Timestamp fechaPago;

    public Long getEstadoCuentaId() {
        return estadoCuentaId;
    }

    public void setEstadoCuentaId(final Long estadoCuentaId) {
        this.estadoCuentaId = estadoCuentaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CuotasForm{" +
                "estadoCuentaId=" + estadoCuentaId +
                ", id=" + id +
                ", fechaVencimiento=" + fechaVencimiento +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
