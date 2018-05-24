package dbaccess.core;

import dao.Dao;
import dbaccess.config.DatasourceType;
import dbaccess.implementations.daos.DaoType;

public abstract class DAOAbstractFactory {

    //FACTORY METHOD
    public static DAOAbstractFactory getDAOFactory(DAOFactory type, DatasourceType datasourceEnum) {
        return type.getInstance(datasourceEnum);
    }

    // There will be a method for each DAO that can be created using factory Method
    public abstract Dao getDao(DaoType daoType);


}

