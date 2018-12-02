package contract;

public interface ConcesionariaServiceContract {
    // todo > add date offset
    String consultarPlanes(String offset);

    String consultarPlan(Long planId);

    void cancelarPlan(Long planId);
}