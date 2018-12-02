package dbaccess;

import dao.NotificationUpdateDAO;
import dbaccess.config.DatasourceEnum;

import java.sql.Connection;
import java.util.function.Function;

public abstract class DAOAbstractFactory {
    //FACTORY METHOD
    public static DAOAbstractFactory getDAOFactory(final DAOFactory type, final DatasourceEnum datasourceEnum, final ClassLoader classLoader) {
        return type.getInstance(datasourceEnum, classLoader);
    }

    // There will be a method for each DAO that can be created using factory Method
    public abstract NotificationUpdateDAO getNotificationUpdateDAO();

    // Add connection service
    public abstract  <A> A withConnection( Function<Connection, A> fn );

}

