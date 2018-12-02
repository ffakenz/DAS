package contract;

import dao.NotificationUpdateDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

public abstract class ConsecionariaService {
    // create mssql factory
	protected DAOAbstractFactory abstractFactory;
	// get dao for plans
	protected NotificationUpdateDAO notificationUpdateDAO;
    public ConsecionariaService(final DAOFactory type) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type, getDatasourceEnum(), getClassLoader());
        notificationUpdateDAO = abstractFactory.getNotificationUpdateDAO();
    }

	abstract protected DatasourceEnum getDatasourceEnum();
	
    abstract protected ClassLoader getClassLoader();
}