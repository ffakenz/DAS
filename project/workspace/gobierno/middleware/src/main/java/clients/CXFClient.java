package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import clients.responses.ClientException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import utils.JsonUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.MiddlewareConstants.*;

public class CXFClient implements ConcesionariaServiceContract {

    private final String wsdlUrl;

    public CXFClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl; // "http://localhost:8000/concesionarias_cxf_one_war/services/concesionaria_cxf_one_service?wsdl"
    }

    public static Optional<ConcesionariaServiceContract> create(final Map<String, String> params) {
        final String wsdlUrl = params.getOrDefault(CXF_PARAM_WSDL_URL, "");
        if (wsdlUrl.isEmpty())
            return Optional.empty();

        final CXFClient cxfClient = new CXFClient(wsdlUrl);
        return Optional.of(cxfClient);
    }

    private <A> Object executeMethod(final String methodName, final A... params) throws ClientException {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        try (final Client client = dcf.createClient(wsdlUrl)) {
            // System.out.println("Consuming Service: " + client.getEndpoint().getService().getName().toString());
            final Object[] res = client.invoke(methodName, params);

            if (res.length == 0)
                throw new ClientException("ENDPOINT IS DOWN = 0");

            return res[0];
        } catch (final Exception e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String identificador, final String offset) throws ClientException {

        final Object object = executeMethod(CONSULTAR_PLANES, identificador, offset);
        final String jsonPlanBeans = object.toString();
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        return Stream.of(notificationUpdates).collect(Collectors.toList());
    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) throws ClientException {
        final Object object = executeMethod(CONSULTAR_PLAN, identificador, planId);
        final String jsonPlanBean = object.toString();
        return JsonUtils.toObject(jsonPlanBean, PlanBean.class);
    }

    @Override
    public void cancelarPlan(final String identificador, final Long planId) throws ClientException {
        executeMethod(CANCELAR_PLAN, identificador, planId);
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final Object object = executeMethod(HEALTH, identificador);
        final String jsonPlanBean = object.toString();
        return jsonPlanBean;
    }
}