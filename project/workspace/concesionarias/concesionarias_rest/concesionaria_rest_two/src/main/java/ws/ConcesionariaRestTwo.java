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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Path("/concesionariaRestTwo")
public class ConcesionariaRestTwo extends MSSQLConsecionaria implements ConcesionariaServiceContract  {

    protected static final Logger log = LoggerFactory.getLogger(ConcesionariaRestTwo.class);

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @GET
    @Path("/consultarPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlanes(@QueryParam("identificador") final String identificador,
                                  @QueryParam("from") final String from,
                                  @QueryParam("to") final String to) {


        final Timestamp fromParsed = Utils.fromStringToTimestamp(from);
        final Timestamp toParsed = Utils.fromStringToTimestamp(to);

        log.debug("Rest consultar planes from -> {} - to -> {} - identificador -> {}", fromParsed.toString(), toParsed.toString(), identificador);

        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(identificador, fromParsed, toParsed));
        return gson.toJson(planes);
    }

    @GET
    @Path("/consultarPlan")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlan(@QueryParam("identificador") final String identificador, @QueryParam("planId") final Long planId) {
        log.debug("Rest consultar plan id -> " + planId + " - identificador -> " + identificador);
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

    @PUT
    @Path("/cancelarPlan")
    @Override
    public void cancelarPlan(@QueryParam("identificador") final String identificador, @QueryParam("planId") final Long planId) {
        log.debug("Rest cancelar plan id -> " + planId + " - identificador -> " + identificador);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(identificador, planId));
    }

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String health(@QueryParam("identificador") final String identificador) {

        log.debug("Rest health identificador -> " + identificador);

        return "OK";
    }
}
