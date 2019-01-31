package ar.edu.ubp.das.src.estado_cuentas.managers;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.Manager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;

public class EstadoCuentasManager extends Manager<MSEstadoCuentasDao> {

    public EstadoCuentasManager(final DaoImpl dao) {
        super(dao);
    }
}
