package clients;

import beans.PlanBean;
import beans.PlanBeanDeserializer;
import com.google.gson.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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
    public List<PlanBean> consultarPlanes() {
        OMElement method = createMethod("consultarPlanes");
        OMElement res = executeMethod(method);


        List<PlanBean> planes = new ArrayList<>();

        Iterator it = res.getChildElements();
        while(it.hasNext()) {
            OMElement returnTag = (OMElement) it.next();
            Iterator itRet = returnTag.getChildElements();
            JsonObject result = deserializeXML(itRet, new JsonObject());

            // deserialization process
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(PlanBean.class, PlanBeanDeserializer.deserializer);
            Gson customGson = gsonBuilder.create();
            PlanBean serializedResult = customGson.fromJson(result, PlanBean.class);
            planes.add(serializedResult);
        }

        return planes;
    }

    @Override
    public PlanBean consultarPlan(Long planId) {
        OMElement method = createMethod("consultarPlan");
        OMElement param = createParam("planId", planId);
        method.addChild(param);


        OMElement res = executeMethod(method); // response

        OMElement returnTag = res.getFirstElement();
        Iterator it = returnTag.getChildElements();
        JsonObject result = deserializeXML(it, new JsonObject());


        // deserialization process
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PlanBean.class, PlanBeanDeserializer.deserializer);
        Gson customGson = gsonBuilder.create();
        PlanBean serializedResult = customGson.fromJson(result, PlanBean.class);

        System.out.println(serializedResult.toString());

        return serializedResult;
    }

    @Override
    public void cancelarPlan(Long planId) {
        OMElement method = createMethod("cancelarPlan");
        OMElement param = createParam("planId", planId);
        method.addChild(param);
        Object res = executeMethod(method);
    }
}
