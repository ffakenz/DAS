package ar.edu.ubp.das.src.jobs.consumo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo.forms.ViewConsumoResultsForm;

import java.sql.SQLException;
import java.util.List;

public class MSViewConsumoResultsDao extends DaoImpl<ViewConsumoResultsForm> {

    public MSViewConsumoResultsDao() {
        super(ViewConsumoResultsForm.class);
    }

    @Override
    public void insert(final ViewConsumoResultsForm form) throws SQLException {

    }

    @Override
    public void update(final ViewConsumoResultsForm form) throws SQLException {

    }

    @Override
    public void delete(final ViewConsumoResultsForm form) throws SQLException {

    }

    @Override
    public List<ViewConsumoResultsForm> select(final ViewConsumoResultsForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_job_results_report");
    }

    @Override
    public boolean valid(final ViewConsumoResultsForm form) throws SQLException {
        return false;
    }
}



