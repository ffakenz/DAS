package ar.edu.ubp.das.mvc.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {

    public abstract T make(ResultSet result) throws SQLException;

    public abstract void insert(T form) throws SQLException;

    public abstract void update(T form) throws SQLException;

    public abstract void delete(T form) throws SQLException;

    public abstract List<T> select(T form) throws SQLException;

    public abstract boolean valid(T form) throws SQLException;

}
