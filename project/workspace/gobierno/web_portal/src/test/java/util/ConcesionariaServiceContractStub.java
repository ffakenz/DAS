package util;

import ar.edu.ubp.das.src.jobs.consumo.ConsumoJob;
import beans.CuotaBean;
import beans.NotificationUpdate;
import beans.PlanBean;
import clients.ConcesionariaServiceContract;
import clients.responses.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Devuelve una List<NotificationUpdate> de a cuerdo a un fileName
class ConcesionariaServiceContractStub implements ConcesionariaServiceContract {
    private final Logger log = LoggerFactory.getLogger(ConsumoJob.class);

    private String notificationFileName;

    public ConcesionariaServiceContractStub(final String notificationFileName) {
        this.notificationFileName = notificationFileName;
    }

    public List<NotificationUpdate> consultarPlanesInner(final String identificador, final Timestamp from, final Timestamp to) throws ClientException {

        log.info("RUNNING [CONSULTAR PLANES][IDENTIFICADOR = {}][FROM= {}][TO= {}]", identificador, from, to);

        if (notificationFileName == null) {
            throw new ClientException("the service is unavailable");
        }

        final String fileContent = MockUtils.readMockBodyFromFile(notificationFileName);
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(fileContent, NotificationUpdate[].class);

        final List<NotificationUpdate> notifications = new ArrayList<>();
        Stream.of(notificationUpdates).forEach(notification -> {
            log.info("[NOTIFICATION UPDATE {}]", notification);
            notifications.add(notification);
        });
        return notifications;
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String identificador, final Timestamp from, final Timestamp to) throws ClientException {
        return consultarPlanesInner(identificador, from, to);
    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) throws ClientException {
        log.info("RUNNING [CONSULTAR PLANES][IDENTIFICADOR = {}][PLAN_ID= {}]", identificador, planId);

        if (notificationFileName == null) {
            throw new ClientException("the service is unavailable");
        }

        final String fileContent = MockUtils.readMockBodyFromFile(notificationFileName);
        final NotificationUpdate notificationUpdate = JsonUtils.toObject(fileContent, NotificationUpdate.class);

        final List<CuotaBean> cuotas = new ArrayList<>();
        cuotas.add(CuotaBean.fromNotificationUpdate(notificationUpdate));

        return PlanBean.fromNotificationUpdate(notificationUpdate, cuotas);
    }

    @Override
    public void notificarGanador(String identificador, Long planId, Long documento) throws ClientException {

    }

    @Override
    public String health(final String identificador) throws ClientException {
        return null;
    }
}

