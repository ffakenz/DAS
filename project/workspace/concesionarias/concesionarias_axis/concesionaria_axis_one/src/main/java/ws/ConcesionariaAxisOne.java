package ws;

import beans.NotificationUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ConcesionariaAxisOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @Override
    public String consultarPlanes(final String offset) {
        System.out.println("Axis consultar planes offset -> " + offset);
        // TODO => Change this using some encoding over offset
        final Timestamp newOffset = Timestamp.valueOf(offset.replace('T', ' '));
        // System.out.println(newOffset.toString());
        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(newOffset));
        return gson.toJson(planes);
    }

    @Override
    public String consultarPlan(final Long planId) {
        System.out.println("Axis consultar plan id -> " + planId);
        final Optional<NotificationUpdate> notificationUpdate =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlan(planId));
        return gson.toJson(notificationUpdate.orElse(new NotificationUpdate()));
    }

    @Override
    public void cancelarPlan(final Long planId) {
        System.out.println("Axis cancelar plan id -> " + planId);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(planId));
    }
}
