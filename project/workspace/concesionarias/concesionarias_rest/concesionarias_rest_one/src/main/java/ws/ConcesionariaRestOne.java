package ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import beans.PlanBean;
import com.google.gson.Gson;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import java.lang.reflect.Type;
import java.util.List;

@Path("/concesionariaRestOne")
public class ConcesionariaRestOne extends MSSQLConsecionaria implements ConcesionariaServiceContract  {


    private Gson gson = new Gson();

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
    public String consultarPlan(@PathParam("planId") Long planId) {
        return gson.toJson(abstractFactory.withConnection(planDAO.consultarPlan(planId)).get());
    }

    @PUT
    @Path("/cancelarPlan")
    @Override
    public void cancelarPlan(@PathParam("planId") Long planId) {
        abstractFactory.withConnection(planDAO.cancelarPlan(planId));
    }
}
