package ar.edu.ubp.das.mvc.db;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void insert(T form) throws SQLException;

    void update(T form) throws SQLException;

    void delete(T form) throws SQLException;

    List<T> select(T form) throws SQLException;

    boolean valid(T form) throws SQLException;

    default List<T> select() throws SQLException {
        return select(null);
    }
}