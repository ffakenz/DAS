package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoExtender<T> extends DaoImpl<T> {
    protected Dao dao;

    public DaoExtender(final Dao dao) {
        this.dao = dao;
    }

    @Override
    public T make(final ResultSet result) throws SQLException {
        return (T) dao.make(result);
    }

    @Override
    public void insert(final T form) throws SQLException {
        dao.insert(form);

    }

    @Override
    public void update(final T form) throws SQLException {
        dao.update(form);

    }

    @Override
    public void delete(final T form) throws SQLException {
        dao.delete(form);

    }

    @Override
    public List<T> select(final T form) throws SQLException {
        return dao.select(form);
    }

    @Override
    public boolean valid(final T form) throws SQLException {
        return dao.valid(form);
    }
}
