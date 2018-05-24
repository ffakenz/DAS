package dbaccess.implementations.daos;

import dao.DaoImpl;
import dbaccess.config.DatasourceConfig;

public enum DaoType {
    CONCESIONARIAS("CONCESIONARIAS") {
        @Override
        public DaoImpl getDao(DatasourceConfig datasource) {
            return new MSSQLConcesionariasDAO(datasource);
        }
    },
    CLIENTES("CLIENTES") {
        @Override
        public DaoImpl getDao(DatasourceConfig datasource) {
            return new MSSQLClientesDAO(datasource);
        }
    },
    ESTADO_CUENTAS("ESTADO_CUENTAS") {
        @Override
        public DaoImpl getDao(DatasourceConfig datasource) {
            return new MSSQLEstadoCuentasDAO(datasource);
        }
    };


    private final String text;
    DaoType(final String text) { this.text = text; }
    @Override
    public String toString() { return text; }

    public abstract DaoImpl getDao(DatasourceConfig datasource);
}
