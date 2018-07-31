package ar.edu.ubp.das.src.estado_cuentas.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCuotaDao extends DaoImpl {

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        final CuotaForm cuotaForm = new CuotaForm();
        cuotaForm.setEstadoCuentaId(result.getLong("estado_cuenta_id"));
        cuotaForm.setId(result.getLong("id"));
        cuotaForm.setFechaVencimiento(result.getTimestamp("fecha_vencimiento"));
        cuotaForm.setMonto(result.getInt("monto"));
        cuotaForm.setFechaPago(result.getTimestamp("fecha_pago"));

        return cuotaForm;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_cuota(?, ?, ?, ?)");
        final CuotaForm f = (CuotaForm) form;
        this.setParameter(1, f.getEstadoCuentaId());
        this.setParameter(2, f.getFechaVencimiento());
        this.setParameter(3, f.getMonto());
        this.setParameter(4, f.getFechaPago());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {
    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_cuota", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<DynaActionForm> cuotas = this.executeQuery();
        this.disconnect();
        return cuotas;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}
