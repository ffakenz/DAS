package ws;

import beans.NotificationUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import java.sql.Timestamp;
import java.util.List;

public class ConcesionariaAxisOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @Override
    public String consultarPlanes(final String offset) {
        final List<NotificationUpdate> planes = abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(Timestamp.valueOf(offset)));
        return gson.toJson(planes);
    }

    @Override
    public String consultarPlan(final Long planId) {
        return gson.toJson(abstractFactory.withConnection(notificationUpdateDAO.consultarPlan(planId)).get());
    }

    @Override
    public void cancelarPlan(final Long planId) {
        System.out.println("Axis cancelar plan id -> " + planId);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(planId));
    }
}
