package ws;

import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import java.util.List;

public class ConcesionariaAxisOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @Override
    public String consultarPlanes() {
        List<PlanBean> planes = abstractFactory.withConnection(planDAO.consultarPlanes());
        return gson.toJson(planes);
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
