package ar.edu.ubp.das.src.jobs.consumo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumo.forms.JobConsumoForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSJobConsumoDao extends DaoImpl<JobConsumoForm> {

    public MSJobConsumoDao() {
        super(JobConsumoForm.class);
    }

    @Override
    public void insert(final JobConsumoForm form) throws SQLException {
        this.executeSimpleProcedure("dbo.job_consumo");
    }

    @Override
    public void update(final JobConsumoForm form) throws SQLException {

    }

    @Override
    public void delete(final JobConsumoForm form) throws SQLException {

    }

    @Override
    public List<JobConsumoForm> select(final JobConsumoForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_consumo_jobs");
    }

    @Override
    public boolean valid(final JobConsumoForm form) throws SQLException {
        return false;
    }

    public JobConsumoForm createJob() throws SQLException {
        this.executeSimpleProcedure("dbo.log_job_consumo");
        return this.getLastJob();
    }

    public JobConsumoForm getLastJob() throws SQLException {
        return this.executeQueryProcedure("dbo.get_last_job").stream().findFirst().get();
    }

    public Optional<JobConsumoForm> getJobById(final Long jobId) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_job_by_id(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, jobId);
        final List<JobConsumoForm> result = this.executeQuery();
        this.disconnect();
        return result
                .stream()
                .findFirst();
    }
}
