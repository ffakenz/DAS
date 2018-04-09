import clients.AxisClient;
import beans.PlanBean;


import clients.CXFClient;
import clients.ConcesionariaServiceContract;
import clients.RestClient;
import com.google.gson.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ClientExample {

    static Consumer<PlanBean> logPlan = plan -> {
        StringBuilder strbr = new StringBuilder();
        strbr.append("PlanID: " + plan.getId());
        strbr.append("CantCoutasPagas: " + plan.getCuotasPagadas());
        strbr.append("Concesionaria: " + plan.getConcesionaria());
        strbr.append("ClientID: " + plan.getClientId());
        strbr.append("Fecha Ultimo Update: " + plan.getFechaUltimoUpdate());

        System.out.println(strbr);
    };

    static Consumer<ConcesionariaServiceContract> logClient = client -> {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSS").create();

        System.out.println("consultarPlanes");
        String planesJSON = client.consultarPlanes(); // use gson
        System.out.println("planes " + planesJSON);
        PlanBean[] planBeans = gson.fromJson(planesJSON, PlanBean[].class);
        Arrays.asList(planBeans).forEach(plan -> logPlan.accept(plan));

        System.out.println("cancelarPlan");
        client.cancelarPlan(3L);

        System.out.println("consultarPlan");
        String planJSON = client.consultarPlan(3L); // use gson
        System.out.println("plan " + planJSON);
        PlanBean plan = gson.fromJson(planJSON, PlanBean.class);
        logPlan.accept(plan);
    };

    public static void main(String[] args) {
        // TODO: quitar/services
        AxisClient axis =
            new AxisClient(
                "http://192.168.1.6:8001/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
                , "http://ws.ConcesionariaAxisOne/"
            );

        // TODO: quitar/services
        CXFClient cxf =
                new CXFClient("http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl");

        // TODO: change concesionarias_rest_one to concesionaria_rest_one
        RestClient rest =
                new RestClient("http://192.168.1.6:8002/concesionarias_rest_one/concesionariaRestOne");


        // System.out.println("AXIS");
        // logClient.accept(axis);
        // System.out.println("CXF");
        // logClient.accept(cxf);
        System.out.println("REST");
        logClient.accept(rest);
    }
}
