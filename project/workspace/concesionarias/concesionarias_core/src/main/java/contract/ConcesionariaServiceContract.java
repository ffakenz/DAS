package contract;

public interface ConcesionariaServiceContract {

    String consultarPlanes(String offset);

    String consultarPlan(Long planId);

    void cancelarPlan(Long planId);
}