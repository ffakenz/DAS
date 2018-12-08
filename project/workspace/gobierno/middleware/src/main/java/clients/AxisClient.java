package clients;

import beans.NotificationUpdate;
import com.google.gson.JsonObject;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import utils.JsonUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AxisClient implements ConcesionariaServiceContract {

    private final String endpointUrl;
    private final OMFactory fac;
    private final OMNamespace omNs;

    public AxisClient(final String endpointUrl // "http://localhost:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
            , final String targetNameSpace // "http://ws.ConcesionariaAxisOne/"
    ) {
        this.endpointUrl = endpointUrl;
        this.fac = OMAbstractFactory.getOMFactory();
        this.omNs = fac.createOMNamespace(targetNameSpace, "ns1");
    }

    public static Optional<ConcesionariaServiceContract> create(final Map<String, String> params) {
        final String endpointUrl = params.getOrDefault("endpointUrl", "");
        final String targetNameSpace = params.getOrDefault("targetNameSpace", "");
        if (endpointUrl.isEmpty() || targetNameSpace.isEmpty())
            return Optional.empty();

        final AxisClient axisClient = new AxisClient(endpointUrl, targetNameSpace);
        return Optional.of(axisClient);
    }

    // TODO: apply DRY on call because is similar to executeMethod.
    private void call(final OMElement method) {
        try {
            final ServiceClient serviceClient = new ServiceClient();
            // create option object
            final Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));
            serviceClient.setOptions(opts);
            serviceClient.fireAndForget(method); // TODO: Note: this is the only line differet
        } catch (final AxisFault axisFault) {
            axisFault.printStackTrace();
            System.out.println(axisFault.getMessage());
        }
    }

    private OMElement executeMethod(final OMElement method) {
        try {
            final ServiceClient serviceClient = new ServiceClient();
            // create option object
            final Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));

            serviceClient.setOptions(opts);

            final OMElement res = serviceClient.sendReceive(method);

            return res;
        } catch (final AxisFault axisFault) {
            axisFault.printStackTrace();
            System.out.println(axisFault.getMessage());
        }
        return null; // non reacheable statemet
    }

    private OMElement createMethod(final String methodName) {
        return fac.createOMElement(methodName, omNs);
    }

    private <A> OMElement createParam(final String paramName, final A paramValue) {
        final OMElement param = fac.createOMElement(paramName, omNs);
        param.setText(paramValue.toString());
        return param;
    }

    // TODO: Move this method to an GSON Utils in order to remove above import com.google.gson.*
    private JsonObject deserializeXML(final Iterator it, final JsonObject bag) {
        if (it.hasNext()) {
            final OMElement child = (OMElement) it.next();
            final String elementName = child.getLocalName();

            if (!child.getChildElements().hasNext())
                bag.addProperty(elementName, child.getText());
            else
                bag.add(elementName, deserializeXML(child.getChildElements(), new JsonObject()));

            return deserializeXML(it, bag);
        }
        return bag;
    }

    @Override
    public List<NotificationUpdate> consultarPlanes(final String offset) {
        final OMElement method = createMethod("consultarPlanes");
        final OMElement param = createParam("offset", offset);
        method.addChild(param);
        final OMElement res = executeMethod(method);

        final OMElement returnValue = res.getFirstElement();
        final String jsonPlanBeans = returnValue.getText();
        final NotificationUpdate[] notificationUpdates = JsonUtils.toObject(jsonPlanBeans, NotificationUpdate[].class);
        return Stream.of(notificationUpdates).collect(Collectors.toList());
    }

    @Override
    public NotificationUpdate consultarPlan(final Long planId) {
        final OMElement method = createMethod("consultarPlan");
        final OMElement param = createParam("planId", planId);
        method.addChild(param);
        final OMElement res = executeMethod(method); // response

        final OMElement returnValue = res.getFirstElement();
        final String jsonPlanBean = returnValue.getText();

        return JsonUtils.toObject(jsonPlanBean, NotificationUpdate.class);
    }

    @Override
    public void cancelarPlan(final Long planId) {
        final OMElement method = createMethod("cancelarPlan");
        final OMElement param = createParam("planId", planId);
        method.addChild(param);
        call(method);
    }
}
