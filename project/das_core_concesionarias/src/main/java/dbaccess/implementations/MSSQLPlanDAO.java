package dbaccess.implementations;

import annotations.MyResultSet;
import beans.PlanBean;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import dao.PlanDAO;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MSSQLPlanDAO implements PlanDAO {
    @Override
    public Function<Connection, List<PlanBean>> consultarPlanes() {

        String consultarPlanesQuery = "SELECT planId, cant_cuotas_pagas, vehiculo, concesionaria, concesionariaId, documento, clientId FROM compradores;";

        return (Connection c) -> {
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery(consultarPlanesQuery);
                List<PlanBean> planes =
                        new MyResultSet<PlanBean>(rs, PlanBean.class).mapToObjectList();
                return planes;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return null; // non reacheable statement
        };
    }

    @Override
    public Function<Connection, Optional<PlanBean>> consultarPlan(Long id) {
        String consultarPlanQuery = "SELECT planId, cant_cuotas_pagas, vehiculo, concesionaria, concesionariaId, documento, clientId FROM compradores WHERE planId = ?;";

        return (Connection c) -> {
            try(PreparedStatement ps = c.prepareStatement(consultarPlanQuery)){
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                PlanBean plan =
                        new MyResultSet<PlanBean>(rs, PlanBean.class).mapToSingleObject();
                return Optional.ofNullable(plan);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return Optional.ofNullable(null); // non reacheable statement
        };
    }

    @Override
    public Function<Connection, Void> cancelarPlan(PlanBean planGanador) {
        String cancelarPlanQuery = "{ CALL cancelarPlan(?) };";

        return (Connection c) -> {
            try(CallableStatement cs = c.prepareCall(cancelarPlanQuery)) {
                c.setAutoCommit(false);

                cs.setInt(1, planGanador.getId());
                cs.execute();

                c.commit();
                c.setAutoCommit(true);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return null;
        };
    }
}
