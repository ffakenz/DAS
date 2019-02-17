package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import clients.responses.ClientException;

import java.sql.Timestamp;
import java.util.List;

public interface ConcesionariaServiceContract {

    List<NotificationUpdate> consultarPlanes(String identificador, Timestamp from, Timestamp to) throws ClientException;

    PlanBean consultarPlan(String identificador, Long planId) throws ClientException;

    void cancelarPlan(String identificador, Long planId) throws ClientException;

    String health(String identificador) throws ClientException;

    default String nanosRepr(final String value) {
        final String dateTimestamp = value.replace('T', ' ');
        final Timestamp day = Timestamp.valueOf(dateTimestamp);
        final Long nanos = day.getTime();
        return String.valueOf(nanos);
    }
}