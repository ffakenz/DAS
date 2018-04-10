import annotations.MyResultSet;
import beans.PlanBean;
import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import dbaccess.config.DatasourceEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Client {
    public static void main(String[] args) {
        // create mssql factory
        DAOAbstractFactory mssqlFactory = DAOAbstractFactory.getDAOFactory(DAOFactory.MSSQL, DatasourceEnum.DEFAULT);
        // get dao for plans
        PlanDAO planDAO = mssqlFactory.getPlanDAO();


        // use api 1
        Function<Connection, List<PlanBean>> all = planDAO.consultarPlanes();
        List<PlanBean> planes =  mssqlFactory.withConnection(planDAO.consultarPlanes());
        planes.forEach(System.out::print);

        /*
        // use api 2
        Optional<PlanBean> plan = mssqlFactory.withConnection(planDAO.consultarPlan(1L)::apply);
        String result = plan.map(p -> p.toString()).get();
        System.out.println(result);

        // use api 3
        mssqlFactory.withConnection(planDAO.cancelarPlan(plan.get().getId().longValue())::apply);

        // use api 2
        Optional<PlanBean> planCancelado = mssqlFactory.withConnection(planDAO.consultarPlan(1L)::apply);
        String resultCancelado = planCancelado.map(p -> p.toString()).get();
        System.out.println(resultCancelado);
        */

        /*mssqlFactory.withConnection((Connection c) -> {
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery("SELECT * FROM compradores");
                List<PlanBean> planes2 =
                        new MyResultSet(rs, PlanBean.class).mapToObjectList();
                return planes2;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return new ArrayList<>();
        });*/
    }
}
