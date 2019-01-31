package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
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
import static clients.factory.ClientType.REST;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

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
    public void test_09() throws Exception {
        // Case: 0 concesionarias

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
    }


    @Test
    public void test_10() throws Exception {
        // Case: 1 concesionaria approved, service down

        ConcesionariaForm concesionariaForm = consumoJobScenarios.setConcesionaria(REST, true);

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        ConsumoForm consumoResult = consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaForm.getId()).orElse(null);

        assertEquals(FAILURE.toString(), consumoResult.getEstado());
    }

    @Test
    public void test_11() throws Exception {
        // Case: 1 concesionaria disapproved
        ConcesionariaForm concesionariaForm = consumoJobScenarios.setConcesionaria(REST, false);

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        ConsumoForm consumoResult =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaForm.getId()).orElse(null);

        assertNull(consumoResult);
    }


}
