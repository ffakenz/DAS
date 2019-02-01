package util;

import clients.ConcesionariaServiceContract;
import clients.factory.ClientType;
import clients.factory.IClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

// Dependiendo del ClientType el file name que levante por consumo
public class ClentFactoryStub implements IClientFactory {
    private Map<ClientType, String> concesionariaXfileName;

    public ClentFactoryStub(Map<ClientType, String> concesionariaXfileName) {
        this.concesionariaXfileName = concesionariaXfileName;
    }

    public Optional<ConcesionariaServiceContract> getClientForMap(ClientType configTecno, Map<String, String> params) {
        if (params.isEmpty())
            return Optional.empty();

        return Optional.of(new ConcesionariaServiceContractStub(concesionariaXfileName.get(configTecno)));
    }

    @Override
    public Optional<ConcesionariaServiceContract> getClientFor(ClientType configTecno, Map<String, String> params) {
        return getClientForMap(configTecno, params);
    }
}
