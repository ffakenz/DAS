package clients.factory;

import clients.*;

import java.util.Map;
import java.util.Optional;

public class ClientFactory implements IClientFactory {

    private static ClientFactory instance = new ClientFactory();

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        return instance;
    }

    @Override
    public Optional<ConcesionariaServiceContract> getClientFor(final ClientType configTecno, final Map<String, String> params) {
        if (params.isEmpty())
            return Optional.empty();

        if (configTecno.equals(ClientType.AXIS)) {
            return AxisClient.create(params);
        } else if (configTecno.equals(ClientType.REST)) {
            return RestClient.create(params);
        } else if (configTecno.equals(ClientType.CXF)) {
            return CXFClient.create(params);
        } else return Optional.empty();
    }
}
