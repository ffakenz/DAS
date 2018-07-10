package clients;

public interface ConcesionariaServiceContract {
	String consultarPlanes();
	String consultarPlan(Long planId);
	void cancelarPlan(Long planId);
}