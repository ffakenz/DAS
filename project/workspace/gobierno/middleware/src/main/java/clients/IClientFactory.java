package clients;

import java.util.Map;
import java.util.Optional;

public interface IClientFactory {
    Optional<ConcesionariaServiceContract> getClientFor(final String configTecno, final Map<String, String> params);
}
