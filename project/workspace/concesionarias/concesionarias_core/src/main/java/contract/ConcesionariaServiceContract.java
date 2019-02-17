package contract;

public interface ConcesionariaServiceContract {

    //  String => Timestamp => List<NotificationUpdate>
    String consultarPlanes(String identificador, String from, String to);

    //  String => Long => PlanBean
    String consultarPlan(String identificador, Long planId);

    // String => Long => ()
    void cancelarPlan(String identificador, Long planId);

    // String => String
    String health(String identificador);
}