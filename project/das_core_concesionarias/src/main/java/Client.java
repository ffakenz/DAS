import beans.PlanBean;
import dao.PlanDAO;
import dbaccess.DAOAbstractFactory;
import dbaccess.DAOFactory;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Client {
    public static void main(String[] args) {
        // create mssql factory
        DAOAbstractFactory mssqlFactory = DAOAbstractFactory.getDAOFactory(DAOFactory.MSSQL);
        // get dao for plans
        PlanDAO planDAO = mssqlFactory.getPlanDAO();


        // use api 1
        Function<Connection, List<PlanBean>> all = planDAO.consultarPlanes();
        List<PlanBean> planes =  mssqlFactory.withConnection(all::apply);
        planes.forEach(System.out::print);

        // use api 2
        Optional<PlanBean> plan = mssqlFactory.withConnection(planDAO.consultarPlan(1L)::apply);
        String result = plan.map(p -> p.toString()).get();
        System.out.println(result);

        // use api 3
        mssqlFactory.withConnection(planDAO.cancelarPlan(plan.get())::apply);

        // use api 2
        Optional<PlanBean> planCancelado = mssqlFactory.withConnection(planDAO.consultarPlan(1L)::apply);
        String resultCancelado = planCancelado.map(p -> p.toString()).get();
        System.out.println(resultCancelado);
    }
}
