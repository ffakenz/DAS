package clients;

import beans.PlanBeanDeserializer;
import com.google.gson.*;
import beans.PlanBean;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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
    public List<PlanBean> consultarPlanes() {
        Object res = executeMethod("consultarPlanes");
        // serialization process
        String jsonPlans = new Gson().toJson(res);

        // deserialization process
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PlanBean.class, PlanBeanDeserializer.deserializer);
        Gson customGson = gsonBuilder.create();

        PlanBean[] planes = customGson.fromJson(jsonPlans, PlanBean[].class);

        List<PlanBean> p = Arrays.asList(planes);
        p.forEach(System.out::println);

        return Arrays.asList(planes);
    }

    @Override
    public PlanBean consultarPlan(Long planId) {
        Object res = executeMethod("consultarPlan", planId);
        // serialization process
        String planJSON = new Gson().toJson(res);

        // deserialization process
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PlanBean.class, PlanBeanDeserializer.deserializer);
        Gson customGson = gsonBuilder.create();
        PlanBean plan = customGson.fromJson(planJSON, PlanBean.class);

        return plan;
    }

    @Override
    public void cancelarPlan(Long planId) {
        executeMethod("cancelarPlan", planId);
    }
}