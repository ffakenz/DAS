package dao;

import beans.NotificationUpdate;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface NotificationUpdateDAO {
    Function<Connection, List<NotificationUpdate>> consultarPlanes(Timestamp offset);
    Function<Connection, Optional<NotificationUpdate>> consultarPlan(Long id);
    Function<Connection, Void> cancelarPlan(Long id);
}