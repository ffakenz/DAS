package ar.edu.ubp.das.src.jobs.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.forms.SorteoForm;

import java.sql.SQLException;
import java.util.List;

public class MSSorteoDao extends DaoImpl<SorteoForm> {

    public MSSorteoDao() {
        super(SorteoForm.class);
    }

    @Override
    public void insert(SorteoForm form) throws SQLException {

    }

    @Override
    public void update(SorteoForm form) throws SQLException {

    }

    @Override
    public void delete(SorteoForm form) throws SQLException {

    }

    @Override
    public List<SorteoForm> select(SorteoForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(SorteoForm form) throws SQLException {
        return false;
    }
}
