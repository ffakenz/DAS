package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.runner.JobRunner;
import ar.edu.ubp.das.src.jobs.sorteo.ISorteoInvariantsHolder;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJob;
import beans.PlanBean;
import clients.ConcesionariaServiceContract;
import clients.IClientFactory;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        final SorteoJob sorteoJob = new SorteoJob(dataSourceConfig, new ClientFactoryMock(), new SorteoInvariantsHolderMock());
        sorteoJob.verificarCancelacionCuenta();
    }

    private class SorteoInvariantsHolderMock implements ISorteoInvariantsHolder {
        @Override
        public Boolean isPlanCancelado(final PlanBean planBeanResponse) {
            return true;
        }
    }

    private class ClientFactoryMock implements IClientFactory {

        @Override
        public Optional<ConcesionariaServiceContract> getClientFor(final String configTecno, final Map<String, String> params) {
            return Optional.of(new ConcesionariaServiceContract() {
                @Override
                public List<PlanBean> consultarPlanes() {
                    return null;
                }

                @Override
                public PlanBean consultarPlan(final Long planId) {
                    return null;
                }

                @Override
                public void cancelarPlan(final Long planId) {

                }
            });
        }
    }
}
