import clients.AxisClient;
import beans.PlanBean;


import clients.CXFClient;
import clients.ConcesionariaServiceContract;
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
        System.out.println("planes" + planesJSON);
        PlanBean[] planBeans = gson.fromJson(planesJSON, PlanBean[].class);
        Arrays.asList(planBeans).forEach(plan -> logPlan.accept(plan));

        System.out.println("cancelarPlan");
        client.cancelarPlan(7L);

        System.out.println("consultarPlane");
        String planJSON = client.consultarPlan(7L); // use gson
        System.out.println("plan" + planJSON);
        PlanBean plan = gson.fromJson(planJSON, PlanBean.class);
        logPlan.accept(plan);
    };


    public static void main(String[] args) {
        AxisClient axis =
            new AxisClient(
                "http://192.168.1.6:8001/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
                , "http://ws.ConcesionariaAxisOne/"
            );

        CXFClient cxf =
                new CXFClient("http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl");

        logClient.accept(cxf);
    }
}
