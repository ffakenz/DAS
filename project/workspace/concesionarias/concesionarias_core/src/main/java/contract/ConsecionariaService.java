package contract;

import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

public abstract class ConsecionariaService {
    // create mssql factory
	protected DAOAbstractFactory abstractFactory;

	// get dao for plans
	protected PlanDAO planDAO;
	
    public ConsecionariaService(DAOFactory type, DatasourceEnum datasourceEnum) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type, datasourceEnum);
        planDAO = abstractFactory.getPlanDAO();
    }     
}