package ws;

import beans.CuotaBean;
import beans.NotificationUpdate;
import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFTwoPort", serviceName = "ConcesionariaCXFTwoService")
public class ConcesionariaCXFTwo extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
    @Override
    public String consultarPlanes(@WebParam(name = "identificador") final String identificador, @WebParam(name = "offset") final String offset) {
        System.out.println("Cxf consultar planes offset -> " + offset + " - identificador -> " + identificador);
        final Timestamp newOffset = Timestamp.valueOf(offset.replace('T', ' '));
        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(identificador, newOffset));
        return gson.toJson(planes);
    }

    @WebMethod(operationName = "consultarPlan", action = "urn:ConsultarPlan")
    @Override
    public String consultarPlan(@WebParam(name = "identificador") final String identificador, @WebParam(name = "planId") final Long planId) {
        System.out.println("Cxf consultar plan id -> " + planId + " - identificador -> " + identificador);
        final List<NotificationUpdate> notificationUpdates =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlan(identificador, planId));

        final List<CuotaBean> cuotas = new ArrayList<>();
        for(final NotificationUpdate n : notificationUpdates) {
            cuotas.add(CuotaBean.fromNotificationUpdate(n));
        }
        if(notificationUpdates.isEmpty())
            return gson.toJson(new PlanBean());

        final NotificationUpdate last = notificationUpdates.get(notificationUpdates.size() - 1);
        final PlanBean plan = PlanBean.fromNotificationUpdate(last, cuotas);
        return gson.toJson(plan);
    }

    @WebMethod(operationName = "cancelarPlan", action = "urn:CancelarPlan")
    @Override
    public void cancelarPlan(@WebParam(name = "identificador") final String identificador, @WebParam(name = "planId") final Long planId) {
        System.out.println("Cxf cancelar plan id -> " + planId + " - identificador -> " + identificador);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(identificador, planId));
    }

    @WebMethod(operationName = "health", action = "urn:Health")
    @Override
    public String health(@WebParam(name = "identificador") final String identificador) {
        System.out.println("Cxf health identificador -> " + identificador);
        return "OK";
    }
}
