package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.consumoo.ConsumoJob;
import ar.edu.ubp.das.src.jobs.consumoo.ConsumoJobManager;
import ar.edu.ubp.das.src.jobs.consumoo.daos.MSConsumoDao;
import ar.edu.ubp.das.src.jobs.consumoo.forms.ConsumoForm;
import clients.factory.ClientFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;

import java.sql.SQLException;

import static ar.edu.ubp.das.src.core.ResponseForward.FAILURE;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumoJobIntegration {

    // Daos
    private DatasourceConfig dataSourceConfig;
    private ConsumoJobScenarios consumoJobScenarios;
    private MSConsumoDao msConsumoDao;
    private ConsumoJobManager consumoJobManager;

    @Before
    public void setup() throws SQLException {
        // Clean DB
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDBConsumoIntegration();

        // Setup Daos
        this.dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        consumoJobScenarios = new ConsumoJobScenarios(dataSourceConfig);

        consumoJobManager = new ConsumoJobManager(dataSourceConfig);
    }

    @Test
    public void it_test_with_0_concesionaria_09() throws Exception {

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
    }


    @Test
    public void it_test_with_1_concesionaria_with_service_down_10() throws Exception {

        consumoJobScenarios.oneRestConcesionaria();

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        ConsumoForm consumoResult = consumoJobManager.getMsConsumoDao().getLastConsumo(1L).get();

        assertEquals(FAILURE.toString(), consumoResult.getEstado());
    }

}
