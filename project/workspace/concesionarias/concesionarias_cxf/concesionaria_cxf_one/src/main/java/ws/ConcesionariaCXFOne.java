package ws;

import beans.NotificationUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFOnePort", serviceName = "ConcesionariaCXFOneService")
public class ConcesionariaCXFOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
    @Override
    public String consultarPlanes(@WebParam(name = "offset") final String offset) {
        System.out.println("Cxf consultar planes offset -> " + offset);
        // TODO => Change this using some encoding over offset
        final Timestamp newOffset = Timestamp.valueOf(offset.replace('T', ' '));
        // System.out.println(newOffset.toString());
        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(newOffset));
        return gson.toJson(planes);
    }

    @WebMethod(operationName = "consultarPlan", action = "urn:ConsultarPlan")
    @Override
    public String consultarPlan(@WebParam(name = "planId") final Long planId) {
        System.out.println("Cxf consultar plan id -> " + planId);
        return gson.toJson(abstractFactory.withConnection(notificationUpdateDAO.consultarPlan(planId)).get());
    }

    @WebMethod(operationName = "cancelarPlan", action = "urn:CancelarPlan")
    @Override
    public void cancelarPlan(@WebParam(name = "planId") final Long planId) {
        System.out.println("Cxf cancelar plan id -> " + planId);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(planId));
    }
}
