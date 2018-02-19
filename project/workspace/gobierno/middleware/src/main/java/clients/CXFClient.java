package clients;

import com.google.gson.*;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import dynamic_proxy.PlanBean;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.rmi.PortableRemoteObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CXFClient implements ConcesionariaServiceContract {

    private final String wsdlUrl;

    // change serialization for specific types
    JsonDeserializer<PlanBean> deserializer = (json, typeOfT, context) -> {
        JsonObject jsonObject = json.getAsJsonObject();

        /// {"clientId":"1-1","concesionaria":"C1","concesionariaId":1,"cuotasPagadas":60,"documento":100,"fechaAlta":{"nanos":0},"fechaUltimoUpdate":{"nanos":797000000},"id":1,"vehiculo":"Corsa"}
        PlanBean plan = new PlanBean(
                jsonObject.get("id").getAsInt()
                , jsonObject.get("cuotasPagadas").getAsInt()
                , jsonObject.get("vehiculo").getAsString()
                , jsonObject.get("concesionaria").getAsString()
                , jsonObject.get("concesionariaId").getAsInt()
                , jsonObject.get("documento").getAsLong()
                , jsonObject.get("clientId").getAsString()
                , new Timestamp(jsonObject.get("fechaAlta").getAsJsonObject().get("nanos").getAsLong())
                , new Timestamp(jsonObject.get("fechaUltimoUpdate").getAsJsonObject().get("nanos").getAsLong())
        );
        return plan;
    };

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
        gsonBuilder.registerTypeAdapter(PlanBean.class, deserializer);
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
        gsonBuilder.registerTypeAdapter(PlanBean.class, deserializer);
        Gson customGson = gsonBuilder.create();
        PlanBean plan = customGson.fromJson(planJSON, PlanBean.class);

        return plan;
    }

    @Override
    public void cancelarPlan(Long planId) {
        executeMethod("cancelarPlan", planId);
    }
}