package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import utils.JsonUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CXFClient implements ConcesionariaServiceContract {

    private final String wsdlUrl;

    public CXFClient(final String wsdlUrl) {
        this.wsdlUrl = wsdlUrl; // "http://localhost:8000/concesionarias_cxf_one_war/services/concesionaria_cxf_one_service?wsdl"
    }

    public static Optional<ConcesionariaServiceContract> create(final Map<String, String> params) {
        final String wsdlUrl = params.getOrDefault("wsdlUrl", "");
        if (wsdlUrl.isEmpty())
            return Optional.empty();

        final CXFClient cxfClient = new CXFClient(wsdlUrl);
        return Optional.of(cxfClient);
    }

    private <A> Object executeMethod(final String methodName, final A... params) {
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        final Client client = dcf.createClient(wsdlUrl);
        try {
            // System.out.println("Consuming Service: " + client.getEndpoint().getService().getName().toString());
            final Object[] res = client.invoke(methodName, params);
            if (res.length == 0)
                return null;
            return res[0];
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("Exception in response is " + e.getMessage());
        } finally {
            client.destroy();
        }
        return null;
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String identificador, final String offset) {
        final Object res = executeMethod("consultarPlanes", identificador, offset);
        final String jsonPlanBeans = res.toString();
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        return Stream.of(notificationUpdates).collect(Collectors.toList());
    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) {
        final Object res = executeMethod("consultarPlan", identificador, planId);
        final String jsonPlanBean = res.toString();
        return JsonUtils.toObject(jsonPlanBean, PlanBean.class);
    }

    @Override
    public void cancelarPlan(final String identificador, final Long planId) {
        executeMethod("cancelarPlan", identificador, planId);
    }

    @Override
    public String health(final String identificador) {
        final Object res = executeMethod("health", identificador);
        final String jsonPlanBean = res.toString();
        return jsonPlanBean;
    }
}