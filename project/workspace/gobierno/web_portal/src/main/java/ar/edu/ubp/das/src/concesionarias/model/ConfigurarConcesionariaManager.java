package ar.edu.ubp.das.src.concesionarias.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.core.Manager;

public class ConfigurarConcesionariaManager extends Manager<MSConcesionariasDao> {

    public ConfigurarConcesionariaManager(final DaoImpl dao) {
        super(dao);
    }
}
