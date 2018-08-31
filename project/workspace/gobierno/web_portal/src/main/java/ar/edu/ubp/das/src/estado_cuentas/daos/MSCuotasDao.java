package ar.edu.ubp.das.src.estado_cuentas.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;

import java.sql.SQLException;
import java.util.List;

public class MSCuotasDao extends DaoImpl<CuotasForm> {


    public MSCuotasDao() {
        super(CuotasForm.class);
    }

    @Override
    public void insert(final CuotasForm bean) throws SQLException {

        this.executeProcedure("dbo.log_cuota(?, ?, ?, ?)", bean,
                "estadoCuentaId", "fechaVencimiento", "monto", "fechaPago");
    }

    @Override
    public void update(final CuotasForm form) throws SQLException {
    }

    @Override
    public void delete(final CuotasForm form) throws SQLException {
    }

    @Override
    public List<CuotasForm> select(final CuotasForm bean) throws SQLException {
        return this.executeQueryProcedure("dbo.get_cuotas", bean);
    }

    @Override
    public boolean valid(final CuotasForm form) throws SQLException {
        return false;
    }

    public List<CuotasForm> selectByEstadoCuenta(final CuotasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_cuotas_by_estado_cuenta_id(?)",
                form, "estadoCuentaId");
    }
}
