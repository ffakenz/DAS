package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.runner.JobRunner;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;
import util.TestDB;

import java.sql.SQLException;

public class JobsSpec {

    DatasourceConfig dataSourceConfig;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        dataSourceConfig = TestDB.getInstance().getDataSourceConfig();
    }


    @Test
    public void test_job_creation() {
        final JobRunner runner = new JobRunner();
        runner.contextInitialized(null);
        System.out.println("----------------------");
        runner.contextDestroyed(null);
    }

    // TESTS SORTEO
    @Test
    public void test_sorteo_base() throws JobExecutionException {

        final SorteoJob sorteoJob = new SorteoJob(dataSourceConfig);
        sorteoJob.verificarCancelacionCuenta();
    }
}
