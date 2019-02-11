package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import clients.responses.ClientException;

import java.sql.Timestamp;
import java.util.List;

public interface ConcesionariaServiceContract {

    List<NotificationUpdate> consultarPlanes(String identificador, String offset) throws ClientException;

    PlanBean consultarPlan(String identificador, Long planId) throws ClientException;

    void cancelarPlan(String identificador, Long planId) throws ClientException;

    String health(String identificador) throws ClientException;

    default String nanosRepr(String value) {
        final String offset = value.replace('T', ' ');
        final Timestamp day = Timestamp.valueOf(offset);
        final Long nanos = day.getTime();
        return String.valueOf(nanos);
    }
}