package contract;

import beans.PlanBean;
import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import java.util.List;
import java.util.Optional;

public abstract class ConsecionariaService {
    // create mssql factory
	protected DAOAbstractFactory abstractFactory;

	// get dao for plans
	protected PlanDAO planDAO;
	
    public ConsecionariaService(DAOFactory type) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type);
        planDAO = abstractFactory.getPlanDAO();
    }     
}