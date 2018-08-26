package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;

import java.sql.SQLException;
import java.util.List;

public class CuotasManager {

    MSCuotasDaoEx msCuotasDaoEx;

    public CuotasManager(final DaoImpl msCuotasDaoEx) {
        this.msCuotasDaoEx = new MSCuotasDaoEx(msCuotasDaoEx);
    }

    public List<CuotasForm> selectAll() throws SQLException {
        return msCuotasDaoEx.select(null);
    }

    public List<CuotasForm> selectByEstadoCuenta(final CuotasForm form) throws SQLException {
        return msCuotasDaoEx.selectByEstadoCuenta(form);
    }

    public void insert(final CuotasForm estadoCuentasFrom) throws SQLException {
        msCuotasDaoEx.insert(estadoCuentasFrom);
    }
}
