package clients;

import beans.NotificationUpdate;
import beans.PlanBean;

import java.util.List;
import java.util.Optional;

public interface ConcesionariaServiceContract {
    Optional<List<NotificationUpdate>> consultarPlanes(String identificador, String offset);

    Optional<PlanBean> consultarPlan(String identificador, Long planId);

    void cancelarPlan(String identificador, Long planId);

    Optional<String> health(String identificador);
}