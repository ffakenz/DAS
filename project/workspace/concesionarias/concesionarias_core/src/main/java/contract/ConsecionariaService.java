package contract;

import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

public abstract class ConsecionariaService {
    // create mssql factory
	protected DAOAbstractFactory abstractFactory;

	abstract protected DatasourceEnum getDatasourceEnum();
    abstract protected ClassLoader getClassLoader();

	// get dao for plans
	protected PlanDAO planDAO;
	
    public ConsecionariaService(DAOFactory type) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type, getDatasourceEnum(), getClassLoader());
        planDAO = abstractFactory.getPlanDAO();
    }     
}