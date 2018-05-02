package dbaccess.implementations;

import annotations.MyResultSet;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import beans.ConcesionariaForm;
import beans.PlanBean;
import dao.ConcesionariaDAO;
import dao.Dao;
import dao.PlanDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MSSQLConcesionariaDAO extends Dao {


    @Override
    public DynaActionForm make(ResultSet result) {
        return null;
    }

    public Function<Connection, List<ConcesionariaForm>> select() {
        String cancelarPlanQuery = "{ CALL get_concesionarias };";

        return (Connection c) -> {
            try(CallableStatement cs = c.prepareCall(cancelarPlanQuery)) {
                ResultSet rs = cs.executeQuery();

                List<ConcesionariaForm> concesionarias =
                        new MyResultSet(rs, ConcesionariaForm.class).mapToObjectList();

                return concesionarias;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return new ArrayList<>();
        };
    }

    @Override
    public Consumer<Connection> insert(DynaActionForm form) {
        return null;
    }

    @Override
    public Consumer<Connection> update(DynaActionForm form) {
        return null;
    }

    @Override
    public Consumer<Connection> delete(DynaActionForm form) {
        return null;
    }
}
