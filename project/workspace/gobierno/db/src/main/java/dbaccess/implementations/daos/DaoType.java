package dbaccess.implementations.daos;

import dao.DaoImpl;
import dbaccess.config.DatasourceConfig;

public enum DaoType {
    INSTANCE("INSTANCE") {
        @Override
        public DaoImpl getDao(DatasourceConfig datasource) {
            return null;
        }
    };

    // this defines above
    private final String text;
    DaoType(final String text) { this.text = text; }
    @Override
    public String toString() { return text; }

    public abstract DaoImpl getDao(DatasourceConfig datasource);
}
