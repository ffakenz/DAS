import beans.PlanBean;
import clients.AxisClient;
import clients.CXFClient;
import clients.ConcesionariaServiceContract;
import clients.RestClient;

public class ClientExample {

    static void consumeService(ConcesionariaServiceContract client) {

        System.out.println("consultarPlanes");
        client.consultarPlanes().forEach(plan -> System.out.println(plan.toString()));

        System.out.println("cancelarPlan");
        client.cancelarPlan(1L);

        System.out.println("consultarPlan");
        PlanBean plan = client.consultarPlan(1L);
        System.out.println(plan.toString());
    }

    public static void main(final String[] args) {

//        runAxis();
        runRest();
//        runCxf();
    }

    private static void runAxis() {
        // TODO: quitar/services
        final AxisClient axis =
                new AxisClient(
                        "http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
                        , "http://ws.ConcesionariaAxisOne/"
                );
        System.out.println("===========================================");
        System.out.println("AXIS");
        consumeService(axis);
    }

    private static void runRest() {
        final RestClient rest =
                new RestClient("http://localhost:8001/concesionaria_rest_one/concesionariaRestOne");

        System.out.println("===========================================");
        System.out.println("REST");
        consumeService(rest);
    }

    private static void runCxf() {
        // TODO: quitar "/services"
        final CXFClient cxf =
                new CXFClient("http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl");


        System.out.println("===========================================");
        System.out.println("CXF");
        consumeService(cxf);
    }
}
