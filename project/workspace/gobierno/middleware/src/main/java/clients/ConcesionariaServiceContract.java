package clients;

import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public interface ConcesionariaServiceContract {
    List<PlanBean> consultarPlanes();

    PlanBean consultarPlan(Long planId);

    void cancelarPlan(Long planId);

    default PlanBean fromJson(final String planJSON) {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSS").create();
        final PlanBean planBean = gson.fromJson(planJSON, PlanBean.class);
        return planBean;
    }

    default List<PlanBean> fromJsonArray(final String planesJSON) {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSS").create();
        final PlanBean[] planBeans = gson.fromJson(planesJSON, PlanBean[].class);
        return Arrays.asList(planBeans);
    }
}