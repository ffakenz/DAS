package ws;

import beans.PlanBean;
import com.google.gson.Gson;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

public class ConcesionariaAxisOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new Gson();

    @Override
    public String consultarPlanes() {
        return gson.toJson(abstractFactory.withConnection(planDAO.consultarPlanes()));
    }

    @Override
    public String consultarPlan(Long planId) {
        return gson.toJson(abstractFactory.withConnection(planDAO.consultarPlan(planId)).get());
    }

    @Override
    public void cancelarPlan(Long planId) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planId));
    }
}
