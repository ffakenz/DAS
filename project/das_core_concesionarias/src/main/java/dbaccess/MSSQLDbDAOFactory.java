package dbaccess;

import dao.PlanDAO;
import dbaccess.DAOFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

public class MSSQLDbDAOFactory extends DAOFactory implements ConnectionConfig {
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public MSSQLDbDAOFactory() {
        //Dependencies injected via ConnectionConfig
        DRIVER = getDriver();
        URL = getDBUrl();
        USERNAME = getUsername();
        PASSWORD = getPassword();
    }

    // method to create Cloudscape connections
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage
        try {
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // functional implementation
    public static void withConnection( Consumer<Connection> c ) {
        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage
        Connection connection = null;
        try {
            Class.forName(DRIVER).newInstance();
            connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
            c.accept(connection);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PlanDAO getPlanDAO() {
        // OracleDbCustomerDAO implements CustomerDAO
        return new MSSQLDbPlanDAO();
    }
}
