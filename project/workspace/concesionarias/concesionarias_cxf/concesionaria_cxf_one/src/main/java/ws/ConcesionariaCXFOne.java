package ws;

import beans.PlanBean;
import com.google.gson.Gson;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFOnePort", serviceName = "ConcesionariaCXFOneService")
public class ConcesionariaCXFOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new Gson();

    @WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
    @Override
    public String consultarPlanes() {
        return gson.toJson(abstractFactory.withConnection(planDAO.consultarPlanes()));
    }

    @WebMethod(operationName = "consultarPlan", action = "urn:ConsultarPlan")
    @Override
    public String consultarPlan(@WebParam(name = "planId") Long planId) {
        return gson.toJson(abstractFactory.withConnection(planDAO.consultarPlan(planId)).get());
    }

    @WebMethod(operationName = "cancelarPlan", action = "urn:CancelarPlan")
    @Override
    public void cancelarPlan(@WebParam(name = "planId") Long planId) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planId));
    }
}
