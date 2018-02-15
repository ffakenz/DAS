package dbaccess;

import dao.PlanDAO;

public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int MSSQL = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract PlanDAO getPlanDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MSSQL:
                return new MSSQLDbDAOFactory();
            default:
                return null;
        }
    }
}
