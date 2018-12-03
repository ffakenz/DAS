package ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;


@Path("/concesionariaRestOne")
public class ConcesionariaRestOne extends MSSQLConsecionaria implements ConcesionariaServiceContract  {


    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @GET
    @Path("/consultarPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlanes(@QueryParam("offset") final String offset) {
        System.out.println("Rest consultar planes offset -> " + offset);
        // TODO => Change this using some encoding over offset
        final Timestamp newOffset = Timestamp.valueOf(offset.replace('T', ' '));
        // System.out.println(newOffset.toString());
        return gson.toJson( abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(newOffset)));
    }

    @GET
    @Path("/consultarPlan")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlan(@QueryParam("planId") final Long planId) {
        System.out.println("Rest consultar plan id -> " + planId);
        return gson.toJson(abstractFactory.withConnection(notificationUpdateDAO.consultarPlan(planId)).get());
    }

    @PUT
    @Path("/cancelarPlan")
    @Override
    public void cancelarPlan(@QueryParam("planId") final Long planId) {
        System.out.println("Rest cancelar plan id -> " + planId);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(planId));
    }
}