package contract;

import java.util.List;
import beans.PlanBean;

public interface ConcesionariaServiceContract {
	public List<PlanBean> consultarPlanes();
	public PlanBean consultarPlan(Long planId);
	public void cancelarPlan(PlanBean planGanador);
}