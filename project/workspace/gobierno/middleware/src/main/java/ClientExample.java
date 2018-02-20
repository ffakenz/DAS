import clients.AxisClient;
import clients.CXFClient;
import beans.PlanBean;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ClientExample {

    public static void log(PlanBean plan) {
        StringBuilder strbr = new StringBuilder();
        strbr.append("PlanID: " + plan.getId());
        strbr.append("CantCoutasPagas: " + plan.getCuotasPagadas());
        strbr.append("Concesionaria: " + plan.getConcesionaria());
        strbr.append("ClientID: " + plan.getClientId());
        strbr.append("Fecha Ultimo Update: " + plan.getFechaUltimoUpdate());

        System.out.println(strbr);
    }




    public static void main(String[] args) {
        AxisClient axis =
            new AxisClient(
                "http://192.168.0.6:8001/concesionarias_axis_one_war/services/ConcesionariaAxisOne.ConcesionariaAxisOneHttpEndpoint/"
                , "http://ws.ConcesionariaAxisOne/"
            );

        PlanBean plan = axis.consultarPlan(1L);
        log(plan);

        /* CXFClient cxf =
            new CXFClient("http://192.168.0.6:8000/concesionarias_cxf_one_war/services/concesionaria_cxf_one_service?wsdl");

        PlanBean plan2 = cxf.consultarPlan(7L);
        log(plan2);

        cxf.cancelarPlan(7L);

        List<PlanBean> planes = cxf.consultarPlanes();
        planes.forEach(ClientExample::log);*/
    }
}