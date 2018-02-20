package clients;

import beans.PlanBean;

import java.util.List;

public interface ConcesionariaServiceContract {
	List<PlanBean> consultarPlanes();
	PlanBean consultarPlan(Long planId);
	void cancelarPlan(Long planId);
}