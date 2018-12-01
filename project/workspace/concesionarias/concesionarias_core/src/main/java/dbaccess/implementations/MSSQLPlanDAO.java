package dbaccess.implementations;

import annotations.MyResultSet;
import beans.PlanBean;
import dao.PlanDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MSSQLPlanDAO implements PlanDAO {
    @Override
    public Function<Connection, List<PlanBean>> consultarPlanes() {

        final String consultarPlanesQuery = "SELECT * FROM planes";

        return (Connection c) -> {
            try (final Statement stm = c.createStatement()) {
                final ResultSet rs = stm.executeQuery(consultarPlanesQuery);
                final List<PlanBean> planes =
                        new MyResultSet<>(rs, PlanBean.class).mapToObjectList();
                return planes;
            } catch (final SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return new ArrayList<>();
        };
    }

    @Override
    public Function<Connection, Optional<PlanBean>> consultarPlan(final Long id) {
        final String consultarPlanQuery = "SELECT * FROM planes WHERE id = ?;";

        return (Connection c) -> {
            try (final PreparedStatement ps = c.prepareStatement(consultarPlanQuery)) {
                ps.setLong(1, id);
                final ResultSet rs = ps.executeQuery();
                final PlanBean plan =
                        new MyResultSet<>(rs, PlanBean.class).mapToSingleObject();
                return Optional.ofNullable(plan);
            } catch (final SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return Optional.empty();
        };
    }

    @Override
    public Function<Connection, Void> cancelarPlan(final Long id) {
        final String cancelarPlanQuery = "{ CALL cancelar_plan(?) };";

        return (Connection c) -> {
            try (final CallableStatement cs = c.prepareCall(cancelarPlanQuery)) {
                c.setAutoCommit(false);

                cs.setLong(1, id);
                cs.execute();

                c.commit();
                c.setAutoCommit(true);

            } catch (final SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return null;
        };
    }
}
