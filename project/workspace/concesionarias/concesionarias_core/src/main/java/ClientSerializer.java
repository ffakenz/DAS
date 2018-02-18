import beans.PlanBean;

import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;

import java.util.Optional;

public class ClientSerializer {
    public static void main(String[] args) {
        // create mssql factory
        DAOAbstractFactory mssqlFactory = DAOAbstractFactory.getDAOFactory(DAOFactory.MSSQL);
        // get dao for plans
        PlanDAO planDAO = mssqlFactory.getPlanDAO();

        // use api 2
        Optional<PlanBean> plan = mssqlFactory.withConnection(planDAO.consultarPlan(1L)::apply);

        // serializer Example
        // String serializedPlan = new Gson().toJson(plan);
        // System.out.println("Plan serializado: " + serializedPlan);

        // deserializer Example
        String jsonInput = "{\"id\":1,\"cuotasPagadas\":60,\"vehiculo\":\"Corsa\",\"concesionaria\":\"C1\",\"concesionariaId\":1,\"documento\":100,\"clientId\":\"1-1\",\"fechaAlta\":\"Feb 8, 2018 8:58:00 PM\",\"fechaUltimoUpdate\":\"Feb 15, 2018 2:44:49 PM\"}";
        // PlanBean deserialized = new Gson().fromJson(jsonInput, PlanBean.class);
        // System.out.println("Plan deserialized : " + deserialized.toString() );
    }
}
