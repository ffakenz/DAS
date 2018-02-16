package ar.edu.ubp.das.ws;

import java.util.List;
import beans.PlanBean;
import contract.implementors.MSSQLConsecionaria;

public class ConcesionariaAxisCore  extends MSSQLConsecionaria {
	
	@Override
	public List<PlanBean> consultarPlanes() {
		return abstractFactory.withConnection(planDAO.consultarPlanes());
	}

	@Override
	public PlanBean consultarPlan(Long planId) {
		return abstractFactory.withConnection(planDAO.consultarPlan(planId)).get();
	}

	@Override
	public void cancelarPlan(PlanBean planGanador) {
		abstractFactory.withConnection(planDAO.cancelarPlan(planGanador));		
	}
}