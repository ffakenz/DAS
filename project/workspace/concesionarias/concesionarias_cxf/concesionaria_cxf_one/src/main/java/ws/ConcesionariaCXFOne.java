package ws;

import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFOnePort", serviceName = "ConcesionariaCXFOneService")
public class ConcesionariaCXFOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
    @Override
    public String consultarPlanes() {
        List<PlanBean> planes = abstractFactory.withConnection(planDAO.consultarPlanes());
        return gson.toJson(planes);
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
