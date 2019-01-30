package ar.edu.ubp.das.src.concesionarias.managers;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.core.Manager;

public class ConcesionariasManager extends Manager<MSConcesionariasDao> {

    public ConcesionariasManager(final DaoImpl dao) {
        super(dao);
    }
}
