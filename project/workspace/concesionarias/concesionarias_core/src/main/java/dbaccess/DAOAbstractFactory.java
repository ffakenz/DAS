package dbaccess;

import dao.PlanDAO;
import dbaccess.config.DatasourceEnum;

import java.sql.Connection;
import java.util.function.Function;

public abstract class DAOAbstractFactory {
    //FACTORY METHOD
    public static DAOAbstractFactory getDAOFactory(DAOFactory type, DatasourceEnum datasourceEnum) {
        return type.getInstance(datasourceEnum);
    }

    // There will be a method for each DAO that can be created using factory Method
    public abstract PlanDAO getPlanDAO();

    // Add connection service
    public abstract  <A> A withConnection( Function<Connection, A> fn );

}

