package ws;

import beans.CuotaBean;
import beans.NotificationUpdate;
import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Path("/concesionariaRestTwo")
public class ConcesionariaRestTwo extends MSSQLConsecionaria implements ConcesionariaServiceContract  {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @GET
    @Path("/consultarPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlanes(@QueryParam("identificador") final String identificador, @QueryParam("offset") final String offset) {
        System.out.println("Rest consultar planes offset -> " + offset + " - identificador -> " + identificador);
        final Timestamp newOffset = Timestamp.valueOf(offset.replace('T', ' '));
        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(identificador, newOffset));
        return gson.toJson(planes);
    }

    @GET
    @Path("/consultarPlan")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlan(@QueryParam("identificador") final String identificador, @QueryParam("planId") final Long planId) {
        System.out.println("Rest consultar plan id -> " + planId + " - identificador -> " + identificador);
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
        System.out.println("Rest cancelar plan id -> " + planId + " - identificador -> " + identificador);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(identificador, planId));
    }

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String health(@QueryParam("identificador") final String identificador) {

        System.out.println("Rest health identificador -> " + identificador);

        return "OK";
    }
}
