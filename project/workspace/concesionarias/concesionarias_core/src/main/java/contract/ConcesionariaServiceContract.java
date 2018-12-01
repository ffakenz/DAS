package contract;

public interface ConcesionariaServiceContract {
    // todo > add date offset
    String consultarPlanes();

    String consultarPlan(Long planId);

    void cancelarPlan(Long planId);
}