package dbaccess.implementations;

import dao.PlanDAO;
import dbaccess.ConnectionConfig;
import dbaccess.DAOAbstractFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Function;

public class MSSQLDAOFactory extends DAOAbstractFactory implements ConnectionConfig {
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public MSSQLDAOFactory() {
        //Dependencies injected via ConnectionConfig
        DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        URL = "jdbc:sqlserver://localhost;databaseName=" + getDBName() + ";integratedSecurity=true;";
        USERNAME = getUsername();
        PASSWORD = getPassword();
    }

    // functional API
    @Override
    public <A> A withConnection( Function<Connection, A> fn ) { // functions comes from MSSQLPlanDAO
        try {
            Class.forName(DRIVER).newInstance();
            try(Connection connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                return fn.apply(connection);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null; // non reacheable statement
    }

    @Override
    public PlanDAO getPlanDAO() {
        return new MSSQLPlanDAO();
    }

}
