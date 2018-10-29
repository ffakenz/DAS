package clients;

import java.util.Map;
import java.util.Optional;

public class ClientFactory {

    private static ClientFactory instance = new ClientFactory();

    private ClientFactory() {
    }

    public static ClientFactory getInstance() {
        return instance;
    }

    public Optional<ConcesionariaServiceContract> getClientFor(final String configTecno, final Map<String, String> params) {
        if (params.isEmpty())
            return Optional.empty();

        if ("AXIS".equalsIgnoreCase(configTecno)) {
            final String endpointUrl = params.getOrDefault("endpointUrl", "");
            final String targetNameSpace = params.getOrDefault("targetNameSpace", "");
            if (endpointUrl.isEmpty() || targetNameSpace.isEmpty())
                return Optional.empty();

            final AxisClient axisClient = new AxisClient(endpointUrl, targetNameSpace);
            return Optional.of(axisClient);
        } else if ("REST".equalsIgnoreCase(configTecno)) {
            final String url = params.getOrDefault("url", "");
            if (url.isEmpty())
                return Optional.empty();

            final RestClient restClient = new RestClient(url);
            return Optional.of(restClient);
        } else if ("CXF".equalsIgnoreCase(configTecno)) {
            final String wsdlUrl = params.getOrDefault("wsdlUrl", "");
            if (wsdlUrl.isEmpty())
                return Optional.empty();

            final CXFClient cxfClient = new CXFClient(wsdlUrl);
            return Optional.of(cxfClient);
        } else return Optional.empty();
    }
}
