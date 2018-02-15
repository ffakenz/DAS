package contract;

import beans.PlanBean;
import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import java.util.List;
import java.util.Optional;

public abstract class ConsecionariaService {
    // create mssql factory
    private DAOAbstractFactory abstractFactory;

    public ConsecionariaService(DAOFactory type) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type);
    }

    // get dao for plans
    private final PlanDAO planDAO = abstractFactory.getPlanDAO();

    public List<PlanBean> consultarPlanes() {
        return abstractFactory.withConnection(planDAO.consultarPlanes());
    };

    public Optional<PlanBean> consultarPlan(Long planId) {
        return abstractFactory.withConnection(planDAO.consultarPlan(planId));
    }
    public void cancelarPlan(PlanBean planGanador) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planGanador));
    }
}