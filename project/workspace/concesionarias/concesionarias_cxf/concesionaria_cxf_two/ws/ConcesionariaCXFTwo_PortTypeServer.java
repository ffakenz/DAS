
package ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.2.2
 * 2019-02-15T20:05:52.074-03:00
 * Generated source version: 3.2.2
 *
 */

public class ConcesionariaCXFTwo_PortTypeServer{

    protected ConcesionariaCXFTwo_PortTypeServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new ws.ConcesionariaCXFTwo();
        String address = "http://localhost:9090/ConcesionariaCXFTwoPort";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws Exception {
        new ConcesionariaCXFTwo_PortTypeServer();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}

