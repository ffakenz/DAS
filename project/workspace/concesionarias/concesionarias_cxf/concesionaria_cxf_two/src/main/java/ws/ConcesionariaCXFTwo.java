package ws;

import beans.CuotaBean;
import beans.NotificationUpdate;
import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utils;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/", portName = "ConcesionariaCXFTwoPort", serviceName = "ConcesionariaCXFTwoService")
public class ConcesionariaCXFTwo extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    protected static final Logger log = LoggerFactory.getLogger(ConcesionariaCXFTwo.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    @WebMethod(operationName = "consultarPlanes", action = "urn:ConsultarPlanes")
    @Override
    public String consultarPlanes(@WebParam(name = "identificador") final String identificador,
                                  @WebParam(name = "from") final String from,
                                  @WebParam(name = "to") final String to) {

        final Timestamp fromParsed = Utils.fromStringToTimestamp(from);
        final Timestamp toParsed = Utils.fromStringToTimestamp(to);
        log.debug("Cxf consultar planes from -> {} - to -> {} - identificador -> {}", fromParsed.toString(), toParsed.toString(), identificador);

        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(
                        notificationUpdateDAO.consultarPlanes(identificador, fromParsed, toParsed)
                );
        return gson.toJson(planes);
    }

    @WebMethod(operationName = "consultarPlan", action = "urn:ConsultarPlan")
    @Override
    public String consultarPlan(@WebParam(name = "identificador") final String identificador, @WebParam(name = "planId") final Long planId) {
        log.debug("Cxf consultar plan id -> " + planId + " - identificador -> " + identificador);
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
        log.debug("Cxf cancelar plan id -> " + planId + " - identificador -> " + identificador);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(identificador, planId));
    }

    @WebMethod(operationName = "health", action = "urn:Health")
    @Override
    public String health(@WebParam(name = "identificador") final String identificador) {
        log.debug("Cxf health identificador -> " + identificador);
        return "OK";
    }
}
