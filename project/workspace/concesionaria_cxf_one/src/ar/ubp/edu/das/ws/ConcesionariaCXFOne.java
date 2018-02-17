package ar.ubp.edu.das.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import beans.PlanBean;
import contract.implementors.MSSQLConsecionaria;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFOnePort", serviceName = "ConcesionariaCXFOneService")
public class ConcesionariaCXFOne extends MSSQLConsecionaria  {
	@WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
	@Override
	public List<PlanBean> consultarPlanes() {
        return abstractFactory.withConnection(planDAO.consultarPlanes());
    };

	@WebMethod(operationName = "consultarPlan", action = "urn:ConsultarPlan")
	@Override
	public PlanBean consultarPlan(@WebParam(name = "planId") Long planId) {
        return abstractFactory.withConnection(planDAO.consultarPlan(planId)).get();
    }

	@WebMethod(operationName = "cancelarPlan", action = "urn:CancelarPlan")
	@Override
	public void cancelarPlan(@WebParam(name = "planGanador") PlanBean planGanador) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planGanador));
    }
}
