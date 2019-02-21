package dao;

import beans.NotificationUpdate;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;

public interface NotificationUpdateDAO {
    Function<Connection, List<NotificationUpdate>> consultarPlanes(String identificador, Timestamp from, Timestamp to);
    Function<Connection, List<NotificationUpdate>> consultarPlan(String identificador, Long id);
    Function<Connection, Void> cancelarPlan(String identificador, Long id, Long documento);
}