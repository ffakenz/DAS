package clients;


import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.Map;

public class AxisClient {

    public static SOAPMessage invoke(QName serviceName, QName portName, String endpointUrl, String soapActionUri) throws Exception {
        /** Create a service and add at least one port to it. **/
        Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointUrl);

        /** Create a Dispatch instance from a service.**/
        Dispatch dispatch = service.createDispatch(portName,
                SOAPMessage.class, Service.Mode.MESSAGE);

        // The soapActionUri is set here. otherwise we get a error on .net based services.
        dispatch.getRequestContext().put(Dispatch.SOAPACTION_USE_PROPERTY, new Boolean(true));
        dispatch.getRequestContext().put(Dispatch.SOAPACTION_URI_PROPERTY, soapActionUri);

        /** Create SOAPMessage request. **/
        // compose a request message
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        //Create objects for the message parts
        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();

        //Populate the Message.  In here, I populate the message from a xml file
        StreamSource preppedMsgSrc = new StreamSource(new FileInputStream("req.xml"));
        soapPart.setContent(preppedMsgSrc);

        //Save the message
        message.saveChanges();

        System.out.println(message.getSOAPBody().getFirstChild().getTextContent());

        SOAPMessage response = (SOAPMessage) dispatch.invoke(message);

        return response;
    }

    public static void exampleOne() {
        String targetNameSpace = "http://ws.ConcesionariaAxisOne/";
        String endpointUrl = "http://localhost:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpSoap11Endpoint/";
        QName serviceName = new QName(targetNameSpace, "ConcesionariaAxisOne");
        QName portName = new QName(targetNameSpace, "ConcesionariaAxisOneHttpSoap11Endpoint");
        String SOAPAction = "urn:consultarPlanes";

        SOAPMessage response;
        try {
            response = invoke(serviceName, portName, endpointUrl, SOAPAction);
            if (response.getSOAPBody().hasFault()) {
                System.out.println(response.getSOAPBody().getFault());
            } else {
                System.out.println("ok");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static OMElement createPayLoad(String op, String paramName, String paramValue) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(
                "http://ws.apache.org/axis2/xsd", "ns1");
        OMElement method = fac.createOMElement(op, omNs);
        OMElement value = fac.createOMElement(paramName, omNs);
        value.setText(paramValue);
        method.addChild(value);
        return method;
    }

    public static void exampleTwo() {
        String configContext = "http://ws.ConcesionariaAxisOne/";
        String wsdlURL = "http://localhost:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne?wsdl";
        String wsdlServiceName = "ConcesionariaAxisOne";
        String portName = "ConcesionariaAxisOneHttpSoap11Endpoint";
        // step 1. create a dynamicClient;
        // ServiceClient dynamicClient = new ServiceClient(null, wsdlURL, new QName(wsdlServiceName), new QName(portName));

        // step 2. then send a payLoad and get a response.
        OMElement res = null;
        // res = dynamicClient.sendReceive(createPayLoad("consultarPlan", "planId", "1l"));
        System.out.println(res);
    }


    public static OMElement createPayLoad() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(
                "http://ws.ConcesionariaAxisOne/", "ns1");
        OMElement method = fac.createOMElement("consultarPlan", omNs);
        OMElement value = fac.createOMElement("planId", omNs);
        value.setText("1");
        method.addChild(value);
        return method;
    }

    public static void exampleThree()
    {
        String endpointUrl =
                "http://localhost:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/";

        try {
            ServiceClient serviceClient;serviceClient = new ServiceClient();

            // create option object
            Options opts = new Options();
            opts.setProperty(HTTPConstants.CHUNKED, false);
            opts.setTo(new EndpointReference(endpointUrl));

            //  >> set the SOAPAction value here !! To fix above error !!
            opts.setAction("http://ws.fraudlabs.com/PostalCodeWorld_Mexico");

            serviceClient.setOptions(opts);

            OMElement res = serviceClient.sendReceive(createPayLoad());
            System.out.println(res);


        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }

    public static void main (String[] args) {
        exampleThree();
    }
}
