package dbaccess.core;

import dbaccess.config.DatasourceType;
import dbaccess.config.ModuleConfigImpl;
import dbaccess.implementations.MSSQLDAOFactory;

public enum DAOFactory {
    // List of DAO types supported by the factory
    // EACH TYPE SHOULD BE COMMA SEPARATED
    MSSQL {
        @Override
        public DAOAbstractFactory getInstance(DatasourceType datasourceId) {
            return new MSSQLDAOFactory(ModuleConfigImpl.getDatasourceById(datasourceId.getValue()));
        }
    };

    public abstract DAOAbstractFactory getInstance(DatasourceType datasourceId);
}

