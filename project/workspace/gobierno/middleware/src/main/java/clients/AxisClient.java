package clients;

import dynamic_proxy.PlanBean;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import java.util.List;

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
            OMElement res = serviceClient.sendReceive(method);
            return res;
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
            System.out.println(axisFault.getMessage());
        }
        return null; // non reacheable statemet
    }
    private final OMElement createMethod(String methodName) {
        return fac.createOMElement("consultarPlanes", omNs);
    }
    private final <A> OMElement createParam(String paramName, A paramValue) {
        OMElement param = fac.createOMElement("consultarPlanes", omNs);
        param.setText(param.toString());
        return param;
    }

    @Override
    public List<PlanBean> consultarPlanes() {
        OMElement method = createMethod("consultarPlanes");
        OMElement res = executeMethod(method);
        System.out.println(res);
        return null;
    }

    @Override
    public PlanBean consultarPlan(Long planId) {
        OMElement method = createMethod("consultarPlan");
        OMElement param = createParam("planId", planId);
        method.addChild(param);
        OMElement res = executeMethod(method);
        System.out.println(res);
        return null;
    }

    @Override
    public void cancelarPlan(Long planId) {
        OMElement method = createMethod("cancelarPlan");
        OMElement param = createParam("planId", planId);
        method.addChild(param);
        OMElement res = executeMethod(method);
        System.out.println(res);
    }
}
