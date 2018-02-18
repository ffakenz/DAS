package ws;

import beans.PlanBean;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

public class ConcesionariaAxisOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {
    @Override
    public List<PlanBean> consultarPlanes() {
        return abstractFactory.withConnection(planDAO.consultarPlanes());
    };


    @Override
    public PlanBean consultarPlan(Long planId) {
        return abstractFactory.withConnection(planDAO.consultarPlan(planId)).get();
    }

    @Override
    public void cancelarPlan(PlanBean planGanador) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planGanador));
    }
}
