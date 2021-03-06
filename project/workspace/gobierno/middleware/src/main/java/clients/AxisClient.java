package clients;

import beans.NotificationUpdate;
import beans.PlanBean;
import clients.responses.ClientException;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
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

public class AxisClient implements ConcesionariaServiceContract {

    private final String endpointUrl;
    private final OMFactory fac;
    private final OMNamespace omNs;

    protected Logger log = LoggerFactory.getLogger(AxisClient.class);

    public AxisClient(final String endpointUrl // "http://localhost:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
            , final String targetNameSpace // "http://ws.ConcesionariaAxisOne/"
    ) {
        this.endpointUrl = endpointUrl;
        this.fac = OMAbstractFactory.getOMFactory();
        this.omNs = fac.createOMNamespace(targetNameSpace, "ns1");
    }

    public static Optional<ConcesionariaServiceContract> create(final Map<String, String> params) {
        final String endpointUrl = params.getOrDefault(AXIS_PARAM_ENDP_URL, "");
        final String targetNameSpace = params.getOrDefault(AXIS_PARAM_TARGET, "");
        if (endpointUrl.isEmpty() || targetNameSpace.isEmpty())
            return Optional.empty();

        final AxisClient axisClient = new AxisClient(endpointUrl, targetNameSpace);
        return Optional.of(axisClient);
    }


    private void call(final OMElement method) throws ClientException {
        try {
            final ServiceClient serviceClient = new ServiceClient();
            // create option object
            final Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));
            serviceClient.setOptions(opts);
            serviceClient.fireAndForget(method); // Note: this is the only line difference with executeMethod

        } catch (final AxisFault e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    private OMElement executeMethod(final OMElement method) throws ClientException {
        try {
            final ServiceClient serviceClient = new ServiceClient();
            // create option object
            final Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));

            serviceClient.setOptions(opts);

            final OMElement res = serviceClient.sendReceive(method);

            if (res.getFirstElement().getText().equals("null"))
                throw new ClientException("ENDPOINT IS DOWN = null");

            return res;
        } catch (final AxisFault e) {
            throw new ClientException("ENDPOINT IS DOWN = " + e.getMessage()); // reached if docker is not running
        }
    }

    private OMElement createMethod(final String methodName) {
        return fac.createOMElement(methodName, omNs);
    }

    private <A> OMElement createParam(final String paramName, final A paramValue) {
        final OMElement param = fac.createOMElement(paramName, omNs);
        param.setText(paramValue.toString());
        return param;
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String identificador, final Timestamp from, final Timestamp to) throws ClientException {
        final OMElement method = createMethod(CONSULTAR_PLANES);
        final OMElement param = createParam(IDENTIFICADOR, identificador);
        final OMElement param2 = createParam(FROM, nanosRepr(from.toString()));
        final OMElement param3 = createParam(TO, nanosRepr(to.toString()));
        method.addChild(param);
        method.addChild(param2);
        method.addChild(param3);
        final OMElement omElement = executeMethod(method);
        final OMElement returnValue = omElement.getFirstElement();
        final String jsonPlanBeans = returnValue.getText();
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        log.info("[GET consultarPlanes][method {}][jsonPlanBeans = {}][notificationUpdates = {}]", method, jsonPlanBeans, notificationUpdates);
        return Stream.of(notificationUpdates).collect(Collectors.toList());
    }

    @Override
    public PlanBean consultarPlan(final String identificador, final Long planId) throws ClientException {
        final OMElement method = createMethod(CONSULTAR_PLAN);
        final OMElement param = createParam(IDENTIFICADOR, identificador);
        final OMElement param2 = createParam(PLANID, planId);
        method.addChild(param);
        method.addChild(param2);
        final OMElement omElement = executeMethod(method); // response

        final OMElement returnValue = omElement.getFirstElement();
        final String jsonPlanBean = returnValue.getText();
        log.info("[GET consultarPlan][method {}][jsonPlanBean = {}]", method, jsonPlanBean);
        return JsonUtils.toObject(jsonPlanBean, PlanBean.class);

    }

    @Override
    public void notificarGanador(final String identificador, final Long planId, final Long documento) throws ClientException {
        final OMElement method = createMethod(NOTIFICAR_GANADOR);
        final OMElement param = createParam(IDENTIFICADOR, identificador);
        final OMElement param2 = createParam(PLANID, planId);
        final OMElement param3 = createParam(DOCUMENTO, documento);
        method.addChild(param);
        method.addChild(param2);
        method.addChild(param3);
        call(method);
        log.info("[POST notificarGanador][method {}]", method);
    }

    @Override
    public String health(final String identificador) throws ClientException {
        final OMElement method = createMethod(HEALTH);
        final OMElement param = createParam(IDENTIFICADOR, identificador);
        method.addChild(param);
        final OMElement omElement = executeMethod(method); // response

        final OMElement returnValue = omElement.getFirstElement();
        final String jsonHealth = returnValue.getText();
        log.info("[GET health][jsonHealth {}]", jsonHealth);
        return jsonHealth;


    }
}
