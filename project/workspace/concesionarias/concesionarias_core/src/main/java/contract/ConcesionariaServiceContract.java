package contract;

public interface ConcesionariaServiceContract {

    //  Timestamp => List<NotificationUpdate>
    String consultarPlanes(String offset);

    //  Long => Optional<NotificationUpdate>
    String consultarPlan(Long planId);

    void cancelarPlan(Long planId);
}