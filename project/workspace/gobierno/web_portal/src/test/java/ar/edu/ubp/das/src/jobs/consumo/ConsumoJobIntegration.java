package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSConsumoDao;
import ar.edu.ubp.das.src.jobs.consumo.forms.ConsumoForm;
import ar.edu.ubp.das.src.jobs.consumo.forms.EstadoConsumo;
import clients.factory.ClientFactory;
import clients.factory.ClientType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.ClentFactoryStub;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;

import java.sql.SQLException;
import java.util.HashMap;

import static ar.edu.ubp.das.src.core.ResponseForward.FAILURE;
import static clients.factory.ClientType.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumoJobIntegration {

    // Daos
    private DatasourceConfig dataSourceConfig;
    private ConsumoJobScenarios consumoJobScenarios;
    private MSConsumoDao msConsumoDao;
    private ConsumoJobManager consumoJobManager;

    private final static String SUCCESS = EstadoConsumo.SUCCESS.getTipo().toUpperCase();

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

        final ConcesionariaForm concesionariaForm = consumoJobScenarios.setConcesionaria(REST, true);

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        final ConsumoForm consumoResult = consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaForm.getId()).orElse(null);

        assertEquals(FAILURE.toString(), consumoResult.getEstado());
    }

    @Test
    public void test_11() throws Exception {
        // Case: 1 concesionaria disapproved
        final ConcesionariaForm concesionariaForm = consumoJobScenarios.setConcesionaria(REST, false);

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        final ConsumoForm consumoResult =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaForm.getId()).orElse(null);

        assertNull(consumoResult);
    }

    @Test
    public void test_12() throws Exception {
        // Case: 1 concesionaria approved with service available return empty list for notification updates
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest.json");
        }};


        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClentFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm consumoResultREST =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);
        System.out.printf(consumoResultREST.toString());

        assertEquals(SUCCESS, consumoResultREST.getEstado());
    }

    @Test
    public void test_13() throws Exception {
        // Case: 3 concesionaria approved with service available return empty list for notification updates
        final ConcesionariaForm concesionariaFormAXIS = consumoJobScenarios.setConcesionaria(AXIS, true);
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);
        final ConcesionariaForm concesionariaFormCXF = consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(AXIS, "notification_update_axis.json");
            put(REST, "notification_update_rest.json");
            put(CXF, "notification_update_cxf.json");
        }};


        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClentFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm consumoResultAXIS =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormAXIS.getId()).orElse(null);

        assertEquals(SUCCESS, consumoResultAXIS.getEstado());

        final ConsumoForm consumoResultREST =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        assertEquals(SUCCESS, consumoResultREST.getEstado());

        final ConsumoForm consumoResultCXF =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormCXF.getId()).orElse(null);

        assertEquals(SUCCESS, consumoResultCXF.getEstado());
    }


}
