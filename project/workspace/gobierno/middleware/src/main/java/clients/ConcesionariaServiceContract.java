package clients;

import beans.NotificationUpdate;

import java.util.List;

public interface ConcesionariaServiceContract {
    List<NotificationUpdate> consultarPlanes(String offset);

    NotificationUpdate consultarPlan(Long planId);

    void cancelarPlan(Long planId);
}