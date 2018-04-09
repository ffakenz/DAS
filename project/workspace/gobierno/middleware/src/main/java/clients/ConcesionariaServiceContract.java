package clients;

import beans.PlanBean;

import java.util.List;

public interface ConcesionariaServiceContract {
	String consultarPlanes();
	String consultarPlan(Long planId);
	void cancelarPlan(Long planId);
}