package dao;

import beans.PlanBean;

import javax.sql.RowSet;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface PlanDAO {
    Function<Connection, List<PlanBean>> consultarPlanes();
    Function<Connection, Optional<PlanBean>> consultarPlan(Long id);
    Function<Connection, Void> cancelarPlan(PlanBean planGanador);
}



