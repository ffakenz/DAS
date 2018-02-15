package dao;

import beans.PlanBean;

import javax.sql.RowSet;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface PlanDAO {
    PlanBean findPlan(Long id);
    boolean cancelPlan(PlanBean plan);
    RowSet selectPlanesRS();
    Collection selectCustomersTO();

    // functional approach
    Function<Connection, List<PlanBean>> findAll();
    Function<Connection, PlanBean> findById(Long id);
    Function<Connection, Boolean> notifyPlanGanador(PlanBean planGanador);
}



