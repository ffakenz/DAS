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
}