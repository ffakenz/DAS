package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoExtender extends DaoImpl {
    protected Dao dao;

    public DaoExtender(final Dao dao) {
        this.dao = dao;
    }

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        return dao.make(result);
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        dao.insert(form);

    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        dao.update(form);

    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {
        dao.delete(form);

    }

    @Override
    public List select(final DynaActionForm form) throws SQLException {
        return dao.select(form);
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return dao.valid(form);
    }
}
