package estado_cuentas.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Timestamp;

public class CuotaForm extends DynaActionForm {
    private Long estadoCuentaId;
    private Long id;
    private Timestamp fechaVencimiento;
    private Integer monto;
    private Timestamp fechaPago;

    public Long getEstadoCuentaId() {
        return estadoCuentaId;
    }

    public void setEstadoCuentaId(Long estadoCuentaId) {
        this.estadoCuentaId = estadoCuentaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "CuotaForm{" +
                "estadoCuentaId=" + estadoCuentaId +
                ", id=" + id +
                ", fechaVencimiento=" + fechaVencimiento +
                ", monto=" + monto +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
