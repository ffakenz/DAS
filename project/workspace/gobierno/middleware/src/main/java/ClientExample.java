import clients.ConcesionariaServiceContract;
import clients.factory.ClientFactory;
import clients.factory.ClientType;
import clients.responses.ClientException;

import java.util.HashMap;

public class ClientExample {

    static void consumeService(final ConcesionariaServiceContract client) throws ClientException {
        final String identificador = "GOB";
//        final Timestamp from = Timestamp.valueOf(ZonedDateTime.now().minusYears(3).toLocalDateTime());
//        final Timestamp to = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());


//        System.out.println("consultarPlanes");
//        final List<NotificationUpdate> notificationUpdates =
//                client.consultarPlanes(identificador, from, to);
//        notificationUpdates.forEach(System.out::println);

        System.out.println("notificarGanador");
        client.notificarGanador(identificador, 2L, 97769788L);

//        System.out.println("consultarPlan");
//        final PlanBean plan0 = client.consultarPlan(identificador, 999L);
//        System.out.println(plan0.toString());

    }

    public static void main(final String[] args) {
        try {
            System.out.println("Running AXIS");
            runAxis();
//            System.out.println("Running REST");
//            runRest();
//            System.out.println("Running CXF");
//            runCxf();
        } catch (final ClientException e) {
            e.printStackTrace();
        }
    }

    private static void runAxis() throws ClientException {
        final String endpointUrl = "http://localhost:8000/concesionaria_axis_one/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/";
        final String targetNameSpace = "http://ws.ConcesionariaAxisOne/";
        final HashMap<String, String> params = new HashMap<>();
        params.put("endpointUrl", endpointUrl);
        params.put("targetNameSpace", targetNameSpace);
        final ConcesionariaServiceContract axis =
                ClientFactory.getInstance().getClientFor(ClientType.AXIS, params).get();

        System.out.println("===========================================");
        System.out.println("AXIS");
        consumeService(axis);
    }

    private static void runRest() throws ClientException {
        final String url = "http://localhost:8001/concesionaria_rest_one/concesionariaRestOne";
        final HashMap<String, String> params = new HashMap<>();
        params.put("url", url);
        final ConcesionariaServiceContract rest =
                ClientFactory.getInstance().getClientFor(ClientType.REST, params).get();

        System.out.println("===========================================");
        System.out.println("REST");
        consumeService(rest);
    }

    private static void runCxf() throws ClientException {
        final String wsdlUrl = "http://localhost:8002/concesionaria_cxf_one/services/concesionaria_cxf_one?wsdl";
        final HashMap<String, String> params = new HashMap<>();
        params.put("wsdlUrl", wsdlUrl);
        final ConcesionariaServiceContract cxf =
                ClientFactory.getInstance().getClientFor(ClientType.CXF, params).get();


        System.out.println("===========================================");
        System.out.println("CXF");
        consumeService(cxf);
    }
}
