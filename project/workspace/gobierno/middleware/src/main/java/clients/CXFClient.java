package clients;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClient {
    public static void main (String[] args) {

        String wsdlUrl =
                "http://localhost:8000/concesionarias_cxf_one_war/services/concesionaria_cxf_one_service?wsdl";
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        try {
            System.out.println("Consuming Service: " + client.getEndpoint().getService().getName().toString());
            // Object plan = Thread.currentThread().getContextClassLoader().loadClass("beans.PlanBean").newInstance();
            Object[] res = client.invoke("consultarPlan", 1L); // cancelar plan
            System.out.println("Response is " + res[0].toString());
        } catch(Exception e ){
            System.out.println("Exception in response is " + e.getMessage());
        }
    }
}
