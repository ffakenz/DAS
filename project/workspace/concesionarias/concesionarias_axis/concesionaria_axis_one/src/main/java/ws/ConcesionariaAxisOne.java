package ws;

import beans.CuotaBean;
import beans.NotificationUpdate;
import beans.PlanBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contract.ConcesionariaServiceContract;
import contract.implementors.MSSQLConsecionaria;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConcesionariaAxisOne extends MSSQLConsecionaria implements ConcesionariaServiceContract {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
            .create();

    @Override
    public String consultarPlanes(final String identificador, final String offset) {
        System.out.println("Axis consultar planes offset -> " + offset + " - identificador -> " + identificador);
        final Timestamp newOffset = Timestamp.valueOf(offset.replace('T', ' '));
        final List<NotificationUpdate> planes =
                abstractFactory.withConnection(notificationUpdateDAO.consultarPlanes(identificador, newOffset));
        return gson.toJson(planes);
    }

    @Override
    public String consultarPlan(final String identificador, final Long planId) {
        System.out.println("Axis consultar plan id -> " + planId + " - identificador -> " + identificador);
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

    @Override
    public void cancelarPlan(final String identificador, final Long planId) {
        System.out.println("Axis cancelar plan id -> " + planId + " - identificador -> " + identificador);
        abstractFactory.withConnection(notificationUpdateDAO.cancelarPlan(identificador, planId));
    }

    @Override
    public String health(final String identificador) {
        System.out.println("Axis health identificador -> " + identificador);
        return "OK";
    }
}
