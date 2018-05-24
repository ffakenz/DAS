package dbaccess.implementations;

import dao.Dao;
import dbaccess.core.DAOAbstractFactory;
import dbaccess.config.DatasourceConfig;
import dbaccess.implementations.daos.DaoType;

public class MSSQLDAOFactory extends DAOAbstractFactory {
    private String DRIVER;
    private String URL;
    private String USERNAME;
    private String PASSWORD;
    private DatasourceConfig datasourceConfig;

    public MSSQLDAOFactory(DatasourceConfig datasource) {
        //Dependencies injected via ConnectionConfig
        DRIVER = datasource.getDriver();
        URL = datasource.getUrl();
        USERNAME = datasource.getUsername();
        PASSWORD = datasource.getPassword();
        datasourceConfig = datasource;
    }

    @Override
    public Dao getDao(DaoType daoType) {
        return daoType.getDao(datasourceConfig);
    }


}
