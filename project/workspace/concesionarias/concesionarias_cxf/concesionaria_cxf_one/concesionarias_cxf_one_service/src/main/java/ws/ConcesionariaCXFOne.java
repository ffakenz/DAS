package ws;

import beans.PlanBean;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFOnePort", serviceName = "ConcesionariaCXFOneService")
public class ConcesionariaCXFOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {
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
