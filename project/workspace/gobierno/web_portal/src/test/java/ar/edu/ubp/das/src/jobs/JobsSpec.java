package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.runner.JobRunner;
import org.junit.Before;
import org.junit.Test;
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
    public void test_job_creation() throws InterruptedException {
        final JobRunner runner = new JobRunner();
        runner.contextInitialized(null);
        System.out.println("----------------------");
        Thread.sleep(10000L);
        runner.contextDestroyed(null);
    }

}
