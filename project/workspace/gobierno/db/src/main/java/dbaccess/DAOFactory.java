package dbaccess;

import dbaccess.config.DatasourceEnum;
import dbaccess.config.ModuleConfigImpl;
import dbaccess.implementations.MSSQLDAOFactory;

public enum DAOFactory {
    // List of DAO types supported by the factory
    // EACH TYPE SHOULD BE COMMA SEPARATED
    MSSQL {
        @Override
        public DAOAbstractFactory getInstance(DatasourceEnum datasourceEnum, ClassLoader classLoader) {
            // TODO: Improve ModuleConfigImpl
            ModuleConfigImpl.load(classLoader);
            return new MSSQLDAOFactory(ModuleConfigImpl.getDatasourceById(datasourceEnum.getValue()));
        }
    };

    public abstract DAOAbstractFactory getInstance(DatasourceEnum datasourceId, ClassLoader classLoader);
}

