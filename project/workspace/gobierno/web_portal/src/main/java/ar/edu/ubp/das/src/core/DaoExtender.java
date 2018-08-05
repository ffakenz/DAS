package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoExtender<T> extends DaoImpl<T> {
    protected DaoImpl<T> dao;

    public DaoExtender(final DaoImpl dao) {
        this.dao = dao;
    }

    @Override
    public T make(final ResultSet result) throws SQLException {
        return dao.make(result);
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
    public List select(final T form) throws SQLException {
        return dao.select(form);
    }

    @Override
    public boolean valid(final T form) throws SQLException {
        return dao.valid(form);
    }
}
