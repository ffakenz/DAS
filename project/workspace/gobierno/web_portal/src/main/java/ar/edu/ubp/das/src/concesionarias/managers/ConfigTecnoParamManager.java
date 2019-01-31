package ar.edu.ubp.das.src.concesionarias.managers;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigTecnoParamDao;
import ar.edu.ubp.das.src.core.Manager;

public class ConfigTecnoParamManager extends Manager<MSConfigTecnoParamDao> {

    public ConfigTecnoParamManager(final DaoImpl dao) {
        super(dao);
    }
}
