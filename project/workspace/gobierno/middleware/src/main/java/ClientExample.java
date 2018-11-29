import beans.PlanBean;
import clients.AxisClient;
import clients.CXFClient;
import clients.ConcesionariaServiceContract;
import clients.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Consumer;

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

        System.out.println("consultarPlanes");
        client.consultarPlanes().forEach(plan -> logPlan.accept(plan));

        System.out.println("cancelarPlan");
        client.cancelarPlan(3L);

        System.out.println("consultarPlan");
        PlanBean plan = client.consultarPlan(3L);
        logPlan.accept(plan);
    };

    public static void main(final String[] args) {
        // TODO: quitar/services
        final AxisClient axis =
                new AxisClient(
                        "http://localhost:8001/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
                        , "http://ws.ConcesionariaAxisOne/"
                );

        // TODO: quitar/services
//        final CXFClient cxf =
//                new CXFClient("http://192.168.1.6:8000/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl");

        // TODO: change concesionarias_rest_one to concesionaria_rest_one
//        final RestClient rest =
//                new RestClient("http://localhost:8002/concesionaria_rest_one/concesionariaRestOne");


        System.out.println("AXIS");
        logClient.accept(axis);
        // System.out.println("CXF");
        // logClient.accept(cxf);
        // System.out.println("REST");
        // logClient.accept(rest);
        // TODO : Create a logClient Unit Test Mockin up the Service Response
    }
}
