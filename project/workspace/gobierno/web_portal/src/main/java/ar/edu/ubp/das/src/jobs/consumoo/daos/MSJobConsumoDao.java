package ar.edu.ubp.das.src.jobs.consumoo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumoo.forms.JobConsumoForm;

import java.sql.SQLException;
import java.util.List;

public class MSJobConsumoDao extends DaoImpl<JobConsumoForm> {

    public MSJobConsumoDao() {
        super(JobConsumoForm.class);
    }

    @Override
    public void insert(final JobConsumoForm form) throws SQLException {

    }

    @Override
    public void update(final JobConsumoForm form) throws SQLException {

    }

    @Override
    public void delete(final JobConsumoForm form) throws SQLException {

    }

    @Override
    public List<JobConsumoForm> select(final JobConsumoForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final JobConsumoForm form) throws SQLException {
        return false;
    }
}
