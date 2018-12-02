package dbaccess.implementations;

import dao.NotificationUpdateDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.config.DatasourceConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class MSSQLDAOFactory extends DAOAbstractFactory {
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public MSSQLDAOFactory(final DatasourceConfig datasource) {
        //Dependencies injected via ConnectionConfig
        DRIVER = datasource.getDriver();
        URL = datasource.getUrl();
        USERNAME = datasource.getUsername();
        PASSWORD = datasource.getPassword();
    }

    // functional API
    @Override
    public <A> A withConnection(final Function<Connection, A> fn ) { // functions comes from MSSQLPlanDAO
        try {
            Class.forName(DRIVER).newInstance();
            try(final Connection connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                return fn.apply(connection);
            }
        } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null; // non reacheable statement
    }

    @Override
    public NotificationUpdateDAO getNotificationUpdateDAO() {
        return new MSSQLNotificationUpdateDAO();
    }

}
