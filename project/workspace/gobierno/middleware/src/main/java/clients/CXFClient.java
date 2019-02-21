package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import clients.responses.ClientException;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.JsonUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static utils.MiddlewareConstants.*;

public class CXFClient implements ConcesionariaServiceContract {

    private final String wsdlUrl;
    protected Logger log = LoggerFactory.getLogger(CXFClient.class);

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

            final Object[] res = client.invoke(methodName, params);

            Long statusCode =
                    Long.parseLong(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());

            if (statusCode >= 500)
                throw new ClientException("ENDPOINT IS DOWN = " + client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString());
            if (statusCode >= 400)
                throw new ClientException("BAD REQUEST = " + client.getResponseContext().get("org.apache.cxf.service.model.MessageInfo").toString());

            if(res == null || res.length == 0) return null;

            return res[0];

        } catch (final Exception e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String identificador, final Timestamp from, final Timestamp to) throws ClientException {

        final String fromParsed = nanosRepr(from.toString());
        final String toParsed = nanosRepr(to.toString());
        final Object object = executeMethod(CONSULTAR_PLANES, identificador, fromParsed, toParsed);
        final String jsonPlanBeans = object.toString();
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        log.info("[GET consultarPlanes][object {}][jsonPlanBeans = {}][notificationUpdates = {}]", object, jsonPlanBeans, notificationUpdates);
        return Stream.of(notificationUpdates).collect(Collectors.toList());
    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) throws ClientException {
        final Object object = executeMethod(CONSULTAR_PLAN, identificador, planId);
        final String jsonPlanBean = object.toString();
        log.info("[GET consultarPlan][object {}][jsonPlanBean = {}]", object, jsonPlanBean);
        return JsonUtils.toObject(jsonPlanBean, PlanBean.class);
    }

    @Override
    public void notificarGanador(String identificador, Long planId, Long documento) throws ClientException {
        executeMethod(NOTIFICAR_GANADOR, identificador, planId, documento);
        log.info("[POST notificarGanador][planId {}][documento:{}]", planId, documento);
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final Object object = executeMethod(HEALTH, identificador);
        final String jsonPlanBean = object.toString();
        log.info("[GET health][jsonPlanBean {}]", jsonPlanBean);
        return jsonPlanBean;
    }
}