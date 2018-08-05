package ar.edu.ubp.das.mvc.db;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

    DynaActionForm make(ResultSet result) throws SQLException;

    void insert(DynaActionForm form) throws SQLException;

    void update(DynaActionForm form) throws SQLException;

    void delete(DynaActionForm form) throws SQLException;

    List<DynaActionForm> select(DynaActionForm form) throws SQLException;

    boolean valid(DynaActionForm form) throws SQLException;

}
