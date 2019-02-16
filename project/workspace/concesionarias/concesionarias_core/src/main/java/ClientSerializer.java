import beans.NotificationUpdate;
import dao.NotificationUpdateDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

import java.util.List;

public class ClientSerializer {
    public static void main(final String[] args) {
        // create mssql factory
        final DAOAbstractFactory mssqlFactory = DAOAbstractFactory.getDAOFactory(DAOFactory.MSSQL, DatasourceEnum.DEFAULT, ClientSerializer.class.getClassLoader());
        // get dao for plans
        final NotificationUpdateDAO notificationUpdate = mssqlFactory.getNotificationUpdateDAO();

        // use api 2
        final List<NotificationUpdate> planes =
                mssqlFactory.withConnection(notificationUpdate.consultarPlan("GOB",1L)::apply);

        // serializer Example
        // String serializedPlan = new Gson().toJson(plan);
        // log.debug("Plan serializado: " + serializedPlan);

        // deserializer Example
        final String jsonInput = "{\"id\":1,\"cuotasPagadas\":60,\"vehiculo\":\"Corsa\",\"concesionaria\":\"C1\",\"concesionariaId\":1,\"documento\":100,\"clientId\":\"1-1\",\"fechaAlta\":\"Feb 8, 2018 8:58:00 PM\",\"fechaUltimoUpdate\":\"Feb 15, 2018 2:44:49 PM\"}";
        // PlanBean deserialized = new Gson().fromJson(jsonInput, PlanBean.class);
        // log.debug("Plan deserialized : " + deserialized.toString() );
    }
}
