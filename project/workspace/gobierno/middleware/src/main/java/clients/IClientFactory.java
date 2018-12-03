package clients;

import clients.factory.ClientType;

import java.util.Map;
import java.util.Optional;

public interface IClientFactory {
    Optional<ConcesionariaServiceContract> getClientFor(final ClientType configTecno, final Map<String, String> params);
}
