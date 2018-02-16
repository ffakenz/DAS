package ar.edu.ubp.das.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import beans.PlanBean;
import contract.implementors.MSSQLConsecionaria;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "ConcesionariaCXFOnePort", serviceName = "ConcesionariaCXFOneService")
public class ConcesionariaCXFOne extends MSSQLConsecionaria  {
	@WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
	@RequestWrapper(className = "ar.edu.ubp.das.ws.jaxws.ConsultarPlanes", localName = "consultarPlanes", targetNamespace = "http://ws.das.ubp.edu.ar/")
	@ResponseWrapper(className = "ar.edu.ubp.das.ws.jaxws.ConsultarPlanesResponse", localName = "consultarPlanesResponse", targetNamespace = "http://ws.das.ubp.edu.ar/")
	@WebResult(name = "return")
	@Override
	public List<PlanBean> consultarPlanes() {
        return abstractFactory.withConnection(planDAO.consultarPlanes());
    };

	@WebMethod(operationName = "consultarPlan", action = "urn:ConsultarPlan")
	@RequestWrapper(className = "ar.edu.ubp.das.ws.jaxws.ConsultarPlan", localName = "consultarPlan", targetNamespace = "http://ws.das.ubp.edu.ar/")
	@ResponseWrapper(className = "ar.edu.ubp.das.ws.jaxws.ConsultarPlanResponse", localName = "consultarPlanResponse", targetNamespace = "http://ws.das.ubp.edu.ar/")
	@WebResult(name = "return")
	@Override
	public PlanBean consultarPlan(@WebParam(name = "arg0") Long planId) {
        return abstractFactory.withConnection(planDAO.consultarPlan(planId)).get();
    }

	@WebMethod(operationName = "cancelarPlan", action = "urn:CancelarPlan")
	@RequestWrapper(className = "ar.edu.ubp.das.ws.jaxws.CancelarPlan", localName = "cancelarPlan", targetNamespace = "http://ws.das.ubp.edu.ar/")
	@ResponseWrapper(className = "ar.edu.ubp.das.ws.jaxws.CancelarPlanResponse", localName = "cancelarPlanResponse", targetNamespace = "http://ws.das.ubp.edu.ar/")
	@WebResult(name = "return")
	@Override
	public void cancelarPlan(@WebParam(name = "arg0") PlanBean planGanador) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planGanador));
    }
}