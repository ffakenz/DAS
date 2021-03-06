import beans.NotificationUpdate;
import dao.NotificationUpdateDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

import java.sql.Timestamp;
import java.util.List;

public class Client {
    public static void main(final String[] args) {

        // create mssql factory
        final DAOAbstractFactory mssqlFactory =
                DAOAbstractFactory.getDAOFactory(DAOFactory.MSSQL, DatasourceEnum.DEFAULT, Client.class.getClassLoader());
        // get dao for plans
        final NotificationUpdateDAO notificationUpdate = mssqlFactory.getNotificationUpdateDAO();


        // use api 1
        // final Function<Connection, List<NotificationUpdate>> all =
        // notificationUpdate .consultarPlanes("GOB", Timestamp.valueOf("2018-01-08 20:58:00"));

        final List<NotificationUpdate> planes =
                mssqlFactory.withConnection(notificationUpdate.consultarPlanes("GOB", Timestamp.valueOf("2018-01-08 20:58:00"), Timestamp.valueOf("2019-01-08 20:58:00")));
        planes.forEach(System.out::println);

        /*
        // use api 2
        Optional<PlanBean> plan = mssqlFactory.withConnection(planDAO.consultarPlan("GOB", 1L)::apply);
        String result = plan.map(p -> p.toString()).get();
        log.debug(result);

        // use api 3
        mssqlFactory.withConnection(planDAO.cancelarPlan("GOB", plan.get().getId().longValue())::apply);

        // use api 2
        Optional<PlanBean> planCancelado = mssqlFactory.withConnection(planDAO.consultarPlan("GOB", 1L)::apply);
        String resultCancelado = planCancelado.map(p -> p.toString()).get();
        log.debug(resultCancelado);
        */

        /*mssqlFactory.withConnection((Connection c) -> {
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery("SELECT * FROM compradores");
                List<PlanBean> planes2 =
                        new MyResultSet(rs, PlanBean.class).mapToObjectList();
                return planes2;
            } catch (SQLException e) {
                log.debug(e.getMessage());
                e.printStackTrace();
            }
            return new ArrayList<>();
        });*/
    }
}
