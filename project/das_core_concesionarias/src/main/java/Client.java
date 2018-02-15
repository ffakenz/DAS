import beans.PlanBean;
import dao.PlanDAO;
import dbaccess.DAOFactory;
import dbaccess.MSSQLDbDAOFactory;

import java.sql.Connection;
import java.util.List;
import java.util.function.Function;

public class Client {
    public static void main(String[] args) {
        DAOFactory mssqlFactory = DAOFactory.getDAOFactory(1);

        PlanDAO planDAO = mssqlFactory.getPlanDAO();

        Function<Connection, List<PlanBean>> all = planDAO.findAll();

        MSSQLDbDAOFactory.withConnection( c-> {
            List<PlanBean> planes = all.apply(c);
            planes.forEach( p -> System.out.print(p) );
        });

    }
}
