package clients;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
public class CXFClient implements ConcesionariaServiceContract {

    private final String wsdlUrl;

    public CXFClient(String wsdlUrl) {
        this.wsdlUrl = wsdlUrl; // "http://localhost:8000/concesionarias_cxf_one_war/services/concesionaria_cxf_one_service?wsdl"
    }

    private final <A> Object executeMethod(String methodName, A ...params) {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        try {
            // System.out.println("Consuming Service: " + client.getEndpoint().getService().getName().toString());
            Object[] res = client.invoke(methodName, params);
            if(res.length == 0)
                return null;
            return res[0];
        } catch(Exception e ){
            e.printStackTrace();
            System.out.println("Exception in response is " + e.getMessage());
        } finally {
            client.destroy();
        }
        return null;
    }

    @Override
    public String consultarPlanes() {
        Object res = executeMethod("consultarPlanes");
        return res.toString();
    }

    @Override
    public String consultarPlan(Long planId) {
        Object res = executeMethod("consultarPlan", planId);
        return res.toString();
    }

    @Override
    public void cancelarPlan(Long planId) {
        executeMethod("cancelarPlan", planId);
    }
}