package util;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;

import java.sql.SQLException;
import java.util.List;

public class TestDB extends DaoImpl<Boolean> {

    private static TestDB instance;
    private DatasourceConfig dataSourceConfig;

    private TestDB() {
        super(Boolean.class);
        dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");
        this.setDatasource(dataSourceConfig);
    }

    public static TestDB getInstance() {
        if (instance == null) {
            instance = new TestDB();
        }
        return instance;
    }

    public void cleanDB() throws SQLException {
        this.executeSimpleProcedure("dbo.cleanDB");
    }

    public void setUpDB() throws SQLException {
        this.executeSimpleProcedure("dbo.setUpDB");
    }

    public DatasourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    @Override
    public void insert(final Boolean form) throws SQLException {

    }

    @Override
    public void update(final Boolean form) throws SQLException {

    }

    @Override
    public void delete(final Boolean form) throws SQLException {

    }

    @Override
    public List<Boolean> select(final Boolean form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final Boolean form) throws SQLException {
        return false;
    }
}
