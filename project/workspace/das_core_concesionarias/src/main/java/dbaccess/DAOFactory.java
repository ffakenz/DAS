package dbaccess;

import dbaccess.implementations.MSSQLDAOFactory;

public enum DAOFactory {
    // List of DAO types supported by the factory
    // EACH TYPE SHOULD BE COMMA SEPARATED
    MSSQL {
        @Override
        public DAOAbstractFactory getInstance() {
            return new MSSQLDAOFactory();
        }
    };

    public abstract DAOAbstractFactory getInstance();
}