package dbaccess;

import annotations.MyResultSet;
import beans.PlanBean;
import dao.PlanDAO;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class MSSQLDbPlanDAO implements PlanDAO {
    public RowSet selectPlanesRS() {
        return null;
    }

    public Collection selectCustomersTO() {
        return null;
    }

    public PlanBean findPlan(Long id) {
        return null;
    }

    public boolean cancelPlan(PlanBean plan) {
        return false;
    }

    @Override
    public Function<Connection, List<PlanBean>> findAll() {
        return (Connection c) -> {
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery("SELECT planId, cant_cuotas_pagas, vehiculo, concesionaria, concesionariaId, documento, clientId FROM compradores;");
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
    public Function<Connection, PlanBean> findById(Long id) {
        return null;
    }

    @Override
    public Function<Connection, Boolean> notifyPlanGanador(PlanBean planGanador) {
        return null;
    }
}
