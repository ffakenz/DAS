package contract;

import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

public abstract class ConsecionariaService {
    // create mssql factory
	protected DAOAbstractFactory abstractFactory;

	abstract protected DatasourceEnum getDatasourceEnum();

	// get dao for plans
	protected PlanDAO planDAO;
	
    public ConsecionariaService(DAOFactory type) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type, getDatasourceEnum());
        planDAO = abstractFactory.getPlanDAO();
    }     
}