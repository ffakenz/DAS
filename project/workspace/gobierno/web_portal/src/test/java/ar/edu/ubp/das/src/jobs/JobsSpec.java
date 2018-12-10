package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.runner.JobRunner;
import ar.edu.ubp.das.src.jobs.sorteo.ISorteoInvariantsHolder;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoInvariantsHolder;
import beans.NotificationUpdate;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;
import util.TestDB;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

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
//        Thread.sleep(10000L);
        runner.contextDestroyed(null);
    }

    @Test
    public void verifyPlanIsCanceladoSuccessfully() {
        final SorteoInvariantsHolder holder = new SorteoInvariantsHolder();
        assertTrue(holder.isPlanCancelado(new NotificationUpdate()));
    }

    // TESTS SORTEO
    @Test
    public void test_sorteo_base() throws JobExecutionException {
        class SorteoInvariantsHolderMock implements ISorteoInvariantsHolder {
            @Override
            public Boolean isPlanCancelado(final NotificationUpdate planBeanResponse) {
                return true;
            }
        }

//        final SorteoJob sorteoJob = new SorteoJob(dataSourceConfig, new ClientFactoryMock(), new SorteoInvariantsHolderMock());
//        sorteoJob.verificarCancelacionCuenta();
    }
}
