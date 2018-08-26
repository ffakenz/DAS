package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.DaoExtender;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;

import java.sql.SQLException;
import java.util.List;

public class MSCuotasDaoEx extends DaoExtender<CuotasForm> {

    public MSCuotasDaoEx(final DaoImpl dao) {
        super(dao, CuotasForm.class);
    }

    public List<CuotasForm> selectByEstadoCuenta(final CuotasForm form) throws SQLException {
        return dao.executeQueryProcedure("dbo.get_cuotas_by_estado_cuenta_id(?)",
                form, "estadoCuentaId");
    }

}
