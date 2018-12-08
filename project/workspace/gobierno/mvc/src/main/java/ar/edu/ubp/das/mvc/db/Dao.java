package ar.edu.ubp.das.mvc.db;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void insert(T form) throws SQLException;

    void update(T form) throws SQLException;

    void delete(T form) throws SQLException;

    List<T> select(T form) throws SQLException;

    default List<T> select() throws SQLException {
        return select(null);
    }

    boolean valid(T form) throws SQLException;

    default void upsert(final T form) throws SQLException {
        if (!this.valid(form)) {
            this.insert(form);
        } else {
            this.update(form);
        }
    }

}
