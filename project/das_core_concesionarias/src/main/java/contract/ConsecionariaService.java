package contract;

import beans.PlanBean;
import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class ConsecionariaService {
    // create mssql factory
    private DAOAbstractFactory abstractFactory;

    public ConsecionariaService(DAOFactory type) {
        abstractFactory = DAOAbstractFactory.getDAOFactory(type);
    }

    // get dao for plans
    private final PlanDAO planDAO = abstractFactory.getPlanDAO();

    public List<PlanBean> consultarPlanes(ConsultarPlanes rqst) {
        return abstractFactory.withConnection(planDAO.consultarPlanes());
    };

    public Optional<PlanBean> consultarPlan(ConsultarPlan rqst) {
        return abstractFactory.withConnection(planDAO.consultarPlan(rqst.planId()));
    }
    public void cancelarPlan(CancelarPlan rqst) {
        abstractFactory.withConnection(planDAO.cancelarPlan(rqst.planGanador()));
    }
}