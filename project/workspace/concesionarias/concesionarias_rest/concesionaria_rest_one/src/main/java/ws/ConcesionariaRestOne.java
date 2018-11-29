package ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;


@Path("/concesionariaRestOne")
public class ConcesionariaRestOne extends MSSQLConsecionaria implements ConcesionariaServiceContract  {


    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @GET
    @Path("/consultarPlanes")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlanes() {
        return gson.toJson( abstractFactory.withConnection(planDAO.consultarPlanes()) );
    }

    @GET
    @Path("/consultarPlan")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String consultarPlan(@QueryParam("planId") Long planId) {
        return gson.toJson(abstractFactory.withConnection(planDAO.consultarPlan(planId)));
    }

    @PUT
    @Path("/cancelarPlan")
    @Override
    public void cancelarPlan(@QueryParam("planId") Long planId) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planId));
    }
}
