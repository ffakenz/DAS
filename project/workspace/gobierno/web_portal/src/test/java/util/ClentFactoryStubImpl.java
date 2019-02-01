package util;

import ar.edu.ubp.das.src.jobs.consumoo.ConsumoJob;
import beans.NotificationUpdate;
import beans.PlanBean;
import clients.ConcesionariaServiceContract;
import clients.factory.ClientType;
import clients.factory.IClientFactory;
import clients.responses.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class ConcesionariaServiceContractStub implements ConcesionariaServiceContract {

    @Override
    public PlanBean consultarPlan(String identificador, Long planId) throws ClientException {
        return null;
    }
    @Override
    public void cancelarPlan(String identificador, Long planId) throws ClientException {

    }
    @Override
    public String health(String identificador) throws ClientException {
        return null;
    }
}

abstract class ClentFactoryStub implements IClientFactory {
    abstract public List<NotificationUpdate> consultarPlanesInner(String identificador, String offset) throws ClientException;

    @Override
    public Optional<ConcesionariaServiceContract> getClientFor(ClientType configTecno, Map<String, String> params) {
        if (params.isEmpty())
            return Optional.empty();

        ConcesionariaServiceContract result = new ConcesionariaServiceContractStub(){
            @Override
            public List<NotificationUpdate> consultarPlanes(String identificador, String offset) throws ClientException {
                return consultarPlanesInner(identificador, offset);
            }
        };
        return Optional.of(result);
    }
}

public class ClentFactoryStubImpl extends ClentFactoryStub {
    private final Logger log = LoggerFactory.getLogger(ConsumoJob.class);

    private String mockFileName;

    public ClentFactoryStubImpl(String mockFileName) {
        this.mockFileName = mockFileName;
    }

    @Override
    public List<NotificationUpdate> consultarPlanesInner(String identificador, String offset) throws ClientException {
        log.info("RUNNING [CONSULTAR PLANES][IDENTIFICADOR = {}][offset= {}]", identificador, offset);
        try {
            String fileContent = MockUtils.readMockBodyFromFile(this.mockFileName);

            NotificationUpdate[] notificationUpdates = JsonUtils.toObject(fileContent, NotificationUpdate[].class);
            Stream.of(notificationUpdates).forEach( notification ->
                    log.info("[NOTIFICATION UPDATE {}]", notification)
            );

            return Stream.of(notificationUpdates).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
