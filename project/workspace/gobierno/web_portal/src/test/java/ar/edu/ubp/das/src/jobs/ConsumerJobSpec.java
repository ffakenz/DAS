package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import beans.NotificationUpdate;
import clients.ConcesionariaServiceContract;
import clients.IClientFactory;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsumerJobSpec {
    DatasourceConfig dataSourceConfig;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpEmptyDB();

        dataSourceConfig = TestDB.getInstance().getDataSourceConfig();
    }

    @Test
    public void test_job_creation() throws InterruptedException {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new ClientFactoryMock());
        consumer.execute(null);
    }

    class ClientFactoryMock implements IClientFactory {

        @Override
        public Optional<ConcesionariaServiceContract> getClientFor(final String configTecno, final Map<String, String> params) {
            return Optional.of(new ConcesionariaServiceContract() {
                @Override
                public List<NotificationUpdate> consultarPlanes(final String offset) {
                    return null;
                }

                @Override
                public NotificationUpdate consultarPlan(final Long planId) {
                    return null;
                }

                @Override
                public void cancelarPlan(final Long planId) {

                }
            });
        }
    }


}
