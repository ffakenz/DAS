package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.Manager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;

public class CuotasManager extends Manager<MSCuotasDao> {

    public CuotasManager(final DaoImpl dao) {
        super(dao);
    }
}
