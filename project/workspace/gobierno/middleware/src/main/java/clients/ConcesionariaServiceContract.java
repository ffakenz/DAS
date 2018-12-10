package clients;

import beans.NotificationUpdate;
import beans.PlanBean;

import java.util.List;

public interface ConcesionariaServiceContract {
    List<NotificationUpdate> consultarPlanes(String identificador, String offset);

    PlanBean consultarPlan(String identificador, Long planId);

    void cancelarPlan(String identificador, Long planId);

    String health(String identificador);
}