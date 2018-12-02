package dbaccess.implementations;

import annotations.MyResultSet;
import beans.NotificationUpdate;
import dao.NotificationUpdateDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MSSQLNotificationUpdateDAO implements NotificationUpdateDAO {
    @Override
    public Function<Connection, List<NotificationUpdate>> consultarPlanes(final Timestamp offset) {

        final String consultarPlanesQuery = "{ CALL consultar_updates(?) };";

        return (Connection c) -> {
            try (final CallableStatement cs = c.prepareCall(consultarPlanesQuery)) {
                cs.setTimestamp(1, offset);
                final ResultSet rs = cs.executeQuery(consultarPlanesQuery);
                final List<NotificationUpdate> planes =
                        new MyResultSet<>(rs, NotificationUpdate.class).mapToObjectList();
                return planes;
            } catch (final SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            return new ArrayList<>();
        };
    }

    @Override
    public Function<Connection, Optional<NotificationUpdate>> consultarPlan(final Long id) {
        final String consultarPlanQuery = "SELECT * FROM notification_updates WHERE plan_id = ?;";

        return (Connection c) -> {
            try (final PreparedStatement ps = c.prepareStatement(consultarPlanQuery)) {
                ps.setLong(1, id);
                final ResultSet rs = ps.executeQuery();
                final NotificationUpdate plan =
                        new MyResultSet<>(rs, NotificationUpdate.class).mapToSingleObject();
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
