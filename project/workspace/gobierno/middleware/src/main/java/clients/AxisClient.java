package clients;

import com.google.gson.JsonObject;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.util.Iterator;

public class AxisClient implements ConcesionariaServiceContract {

    private final String endpointUrl;
    private final OMFactory fac;
    private final OMNamespace omNs;

    public AxisClient(String endpointUrl // "http://localhost:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
                      , String targetNameSpace // "http://ws.ConcesionariaAxisOne/"
    ) {
        this.endpointUrl = endpointUrl;
        this.fac = OMAbstractFactory.getOMFactory();
        this.omNs = fac.createOMNamespace(targetNameSpace, "ns1");
    }

    // TODO: apply DRY on call because is similar to executeMethod.
    private final void call(OMElement method) {
        try {
            ServiceClient serviceClient = new ServiceClient();
            // create option object
            Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));
            serviceClient.setOptions(opts);
            serviceClient.fireAndForget(method); // TODO: Note: this is the only line differet
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
            System.out.println(axisFault.getMessage());
        }
    }

    private final OMElement executeMethod(OMElement method) {
        try {
            ServiceClient serviceClient = new ServiceClient();
            // create option object
            Options opts = new Options();
            opts.setTo(new EndpointReference(endpointUrl));

            serviceClient.setOptions(opts);

            OMElement res = serviceClient.sendReceive(method);

            return res;
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
            System.out.println(axisFault.getMessage());
        }
        return null; // non reacheable statemet
    }
    private final OMElement createMethod(String methodName) {
        return fac.createOMElement(methodName, omNs);
    }
    private final <A> OMElement createParam(String paramName, A paramValue) {
        OMElement param = fac.createOMElement(paramName, omNs);
        param.setText(paramValue.toString());
        return param;
    }

    // TODO: Move this method to an GSON Utils in order to remove above import com.google.gson.*
    private final JsonObject deserializeXML(Iterator it, JsonObject bag) {
        if(it.hasNext()) {
            OMElement child = (OMElement) it.next();
            String elementName = child.getLocalName();

            if(!child.getChildElements().hasNext())
                bag.addProperty(elementName, child.getText());
            else
                bag.add(elementName, deserializeXML(child.getChildElements(), new JsonObject()));

            return deserializeXML(it, bag);
        }
        return bag;
    }

    @Override
    public String consultarPlanes() {
        OMElement method = createMethod("consultarPlanes");
        OMElement res = executeMethod(method);

        OMElement returnValue = res.getFirstElement();
        return returnValue.getText();
    }

    @Override
    public String consultarPlan(Long planId) {
        OMElement method = createMethod("consultarPlan");
        OMElement param = createParam("planId", planId);
        method.addChild(param);
        OMElement res = executeMethod(method); // response

        OMElement returnValue = res.getFirstElement();
        return returnValue.getText();
    }

    @Override
    public void cancelarPlan(Long planId) {
        OMElement method = createMethod("cancelarPlan");
        OMElement param = createParam("planId", planId);
        method.addChild(param);
        call(method);
    }
}
