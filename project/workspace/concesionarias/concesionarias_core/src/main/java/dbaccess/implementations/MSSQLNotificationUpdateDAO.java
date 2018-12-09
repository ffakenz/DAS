package dbaccess.implementations;

import annotations.MyResultSet;
import beans.NotificationUpdate;
import dao.NotificationUpdateDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MSSQLNotificationUpdateDAO implements NotificationUpdateDAO {
    @Override
    public Function<Connection, List<NotificationUpdate>> consultarPlanes(final String identificador, final Timestamp offset) {

        final String consultarPlanesQuery = "{ CALL dbo.consultar_updates(?, ?) };";

        return (Connection c) -> {
            try (final CallableStatement cs = c.prepareCall(consultarPlanesQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                cs.setString(1, identificador);
                cs.setTimestamp(2, offset);
                final ResultSet rs = cs.executeQuery();
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
    public Function<Connection, List<NotificationUpdate>> consultarPlan(final String identificador, final Long planId) {
        final String consultarPlanQuery =
                  "SELECT * "
                + "FROM notification_updates "
                + "WHERE "
                +     "plan_tipo_de_plan = ? "
                +     "AND plan_id = ? "
                + "ORDER BY plan_fecha_ultima_actualizacion, cuota_nro_cuota ASC";

        return (Connection c) -> {
            try (final PreparedStatement ps = c.prepareStatement(consultarPlanQuery)) {
                ps.setString(1, identificador);
                ps.setLong(2, planId);
                final ResultSet rs = ps.executeQuery();
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
    public Function<Connection, Void> cancelarPlan(final String identificador, final Long planId) {
        final String cancelarPlanQuery = "{ CALL cancelar_plan(?, ?) };";

        return (Connection c) -> {
            try (final CallableStatement cs = c.prepareCall(cancelarPlanQuery)) {
                c.setAutoCommit(false);

                cs.setString(1, identificador);
                cs.setLong(2, planId);
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
