package client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WSClient {

    public WSClient() {
    }

    public static void main (String[] args) {

        String wsdlUrl = "http://localhost:8080/doubleit_war/services/doubleit_service?wsdl";
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        try {
            System.out.println("Consuming Service: " + client.getEndpoint().getService().getName().toString());
            // Object plan = Thread.currentThread().getContextClassLoader().loadClass("beans.PlanBean").newInstance();
            Object[] res = client.invoke("doubleIt", 10); // cancelar plan
            System.out.println("Response is " + res[0].toString());
        } catch(Exception e ){
            System.out.println("Exception in response is " + e.getMessage());
        }


    }

}
