package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;

import java.sql.SQLException;
import java.util.List;

public class CuotasManager {

    MSCuotasDao msCuotasDao;

    public CuotasManager(final DaoImpl msCuotasDao) {
        this.msCuotasDao = (MSCuotasDao) msCuotasDao;
    }

    public List<CuotasForm> selectAll() throws SQLException {
        return msCuotasDao.select(null);
    }

    public List<CuotasForm> selectByEstadoCuenta(final CuotasForm form) throws SQLException {
        return msCuotasDao.selectByEstadoCuenta(form);
    }

    public void insert(final CuotasForm estadoCuentasFrom) throws SQLException {
        msCuotasDao.insert(estadoCuentasFrom);
    }
}
