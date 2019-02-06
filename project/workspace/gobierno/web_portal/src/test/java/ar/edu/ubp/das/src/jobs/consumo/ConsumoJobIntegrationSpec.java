package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.jobs.consumo.forms.ConsumoForm;
import ar.edu.ubp.das.src.jobs.consumo.forms.EstadoConsumo;
import clients.factory.ClientFactory;
import clients.factory.ClientType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.ClientFactoryStub;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static ar.edu.ubp.das.src.core.ResponseForward.FAILURE;
import static clients.factory.ClientType.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumoJobIntegrationSpec {

    // Daos
    private DatasourceConfig dataSourceConfig;
    private ConsumoJobScenarios consumoJobScenarios;
    private MSConcesionariasDao msConcesionariasDao;
    private MSConfigurarConcesionariaDao msConfigurarConcesionariaDao;
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

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

        msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test_09() throws Exception {
        // Case: 0 concesionarias in db

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(0, consumoJobManager.getMsConsumoDao().select(new ConsumoForm()).size());
    }

    @Test
    public void test_10() throws Exception {
        // Case: 1 concesionaria disapproved
        final ConcesionariaForm concesionariaForm = consumoJobScenarios.setConcesionaria(REST, false);

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        final ConsumoForm consumoResult =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaForm.getId()).orElse(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertNull(consumoResult);
        assertEquals(0, consumoJobManager.getMsConsumoDao().select(new ConsumoForm()).size());
    }

    @Test
    public void test_11() throws Exception {
        // Case: 1 concesionaria approved with service unavailable
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);

        // set invalid url to the service, this is to get unavailable service (the causes may be a lot we test only this)
        List<ConfigurarConcesionariaForm> configurarConcesionariaForms = msConfigurarConcesionariaDao.selectParamsByConcesionariaId(concesionariaFormREST.getId());

        // we know that we have a rest concesionaria, so they have only one param
        configurarConcesionariaForms.get(0).setValue("url.invalida.com");
        msConfigurarConcesionariaDao.upsert(configurarConcesionariaForms.get(0));

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());

        consumer.execute(null);

        final ConsumoForm lastConsumoForConcesionaria =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertNotNull(lastConsumoForConcesionaria);
        assertEquals(FAILURE.toString(), lastConsumoForConcesionaria.getEstado());
    }

    @Test
    public void test_12() throws Exception {
        // Case: 1 concesionaria approved with service available return empty list for notification updates
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest_empty.json");
        }};


        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm lastConsumoForConcesionaria =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertNotNull(lastConsumoForConcesionaria);
        assertEquals(SUCCESS.toString(), lastConsumoForConcesionaria.getEstado());
        assertEquals(0, consumoJobManager.getMsConsumoResultDao().select(null).size());
    }

    @Test
    public void test_13() throws Exception {
        // Case: 1 concesionaria approved with service available return notification updates ok
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm lastConsumoForConcesionaria =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertNotNull(lastConsumoForConcesionaria);
        assertEquals(SUCCESS.toString(), lastConsumoForConcesionaria.getEstado());
        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertEquals(SUCCESS.toString(), consumoJobManager.getMsConsumoResultDao().select(null).get(0).getResult());
    }

    @Test
    public void test_14() throws Exception {
        // Case: 1 concesionaria approved with service available return invalid notification update and fail save
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest_invalid.json");
        }};


        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm lastConsumoForConcesionaria =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertNotNull(lastConsumoForConcesionaria);
        assertEquals(SUCCESS.toString(), lastConsumoForConcesionaria.getEstado());
        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertEquals(FAILURE.toString(), consumoJobManager.getMsConsumoResultDao().select(null).get(0).getResult());
    }

    @Test
    public void test_15() throws Exception {
        // Case: 2 concesionaria, 1 approved, with service available , save notification update valid
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);
        final ConcesionariaForm concesionariaFormCxf = consumoJobScenarios.setConcesionaria(CXF, false);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm lastConsumoForConcesionariaRest =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        final ConsumoForm lastConsumoForConcesionariaCxf =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormCxf.getId()).orElse(null);

        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertNotNull(lastConsumoForConcesionariaRest);
        assertNull(lastConsumoForConcesionariaCxf);
        assertEquals(SUCCESS.toString(), lastConsumoForConcesionariaRest.getEstado());
        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertEquals(SUCCESS.toString(), consumoJobManager.getMsConsumoResultDao().select(null).get(0).getResult());
    }

    @Test
    public void test_16() throws Exception {
        // Case: 2 concesionaria, 2 approved, 1 service available, save notification update valid
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);
        final ConcesionariaForm concesionariaFormCxf = consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        final ConsumoForm lastConsumoForConcesionariaRest =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormREST.getId()).orElse(null);

        final ConsumoForm lastConsumoForConcesionariaCxf =
                consumoJobManager.getMsConsumoDao().getLastConsumo(concesionariaFormCxf.getId()).orElse(null);

        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts for cxf
        assertNotNull(lastConsumoForConcesionariaCxf);
        assertEquals(FAILURE.toString(), lastConsumoForConcesionariaCxf.getEstado());
        // asserts for rest
        assertNotNull(lastConsumoForConcesionariaRest);
        assertEquals(SUCCESS.toString(), lastConsumoForConcesionariaRest.getEstado());

        // asserts for logs consumo db and results
        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertEquals(SUCCESS.toString(), consumoJobManager.getMsConsumoResultDao().select(null).get(0).getResult());
    }

    @Test
    public void test_17() throws Exception {
        // Case: 2 concesionaria, 2 approved, 2 service available, 2 save notification update valid
        final ConcesionariaForm concesionariaFormREST = consumoJobScenarios.setConcesionaria(REST, true);
        final ConcesionariaForm concesionariaFormCxf = consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest.json");
            put(CXF, "notification_update_cxf.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);


        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts on consumo
        List<ConsumoForm> allConsumos = consumoJobManager.getMsConsumoDao().select(null);
        assertEquals(2, allConsumos.size());
        assertTrue(allConsumos.stream()
                .allMatch(c -> c.getEstado().equals(SUCCESS.toString()))
        );

        // asserts for logs consumo db and results
        assertEquals(2, consumoJobManager.getMsConsumoResultDao().select(null).size());

        assertTrue(consumoJobManager.getMsConsumoResultDao().select(null)
                .stream()
                .allMatch( c -> c.getResult().equals(SUCCESS.toString()))
        );
    }

    @Test
    public void test_18() throws Exception {
        // Case: 2 concesionaria, 2 approved, 2 service available, 2 save notification with 1 invalid for one conc, and 1 save notification valid for the another conc
        consumoJobScenarios.setConcesionaria(REST, true);
        consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest_one_valid_one_invalid.json");
            put(CXF, "notification_update_cxf.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts on consumo
        List<ConsumoForm> allConsumos = consumoJobManager.getMsConsumoDao().select(null);
        assertEquals(2, allConsumos.size());
        assertTrue(allConsumos.stream()
                .allMatch(c -> c.getEstado().equals(SUCCESS))
        );

        // asserts for logs consumo db and results
        assertEquals(3, consumoJobManager.getMsConsumoResultDao().select(null).size());

        assertEquals(2, consumoJobManager.getMsConsumoResultDao().select(null)
                .stream()
                .filter( c -> c.getResult().equals(SUCCESS))
                .collect(Collectors.toList())
                .size()
        );

        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null)
                .stream()
                .filter( c -> c.getResult().equals(FAILURE.toString()))
                .collect(Collectors.toList())
                .size()
        );
    }

    @Test
    public void test_19() throws Exception {
        // Case: 2 concesionaria, 2 approved, 1 service available, 1 save notification valid
        consumoJobScenarios.setConcesionaria(REST, true);
        consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts on consumo
        List<ConsumoForm> allConsumos = consumoJobManager.getMsConsumoDao().select(null);
        assertEquals(2, allConsumos.size());

        assertEquals(1, allConsumos.stream()
                .filter(c -> c.getEstado().equals(SUCCESS))
                .collect(Collectors.toList())
                .size()
        );
        assertEquals(1, allConsumos.stream()
                .filter(c -> c.getEstado().equals(FAILURE.toString()))
                .collect(Collectors.toList())
                .size()
        );

        // asserts for logs consumo db and results
        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertEquals(SUCCESS, consumoJobManager.getMsConsumoResultDao().select(null).get(0).getResult());
    }

    @Test
    public void test_20() throws Exception {
        // Case: 2 concesionaria, 2 approved, 1 service available, 1 save notification invalid
        consumoJobScenarios.setConcesionaria(REST, true);
        consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest_invalid.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts on consumo
        List<ConsumoForm> allConsumos = consumoJobManager.getMsConsumoDao().select(null);
        assertEquals(2, allConsumos.size());

        assertEquals(1, allConsumos.stream()
                .filter(c -> c.getEstado().equals(SUCCESS))
                .collect(Collectors.toList())
                .size()
        );
        assertEquals(1, allConsumos.stream()
                .filter(c -> c.getEstado().equals(FAILURE.toString()))
                .collect(Collectors.toList())
                .size()
        );

        // asserts for logs consumo db and results
        assertEquals(1, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertEquals(FAILURE.toString(), consumoJobManager.getMsConsumoResultDao().select(null).get(0).getResult());
    }

    @Test
    public void test_21() throws Exception {
        // Case: 2 concesionaria, 2 approved, 1 service available, 0 save notification update
        consumoJobScenarios.setConcesionaria(REST, true);
        consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "notification_update_rest_empty.json");
        }};

        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts on consumo
        List<ConsumoForm> allConsumos = consumoJobManager.getMsConsumoDao().select(null);
        assertEquals(2, allConsumos.size());

        assertEquals(1, allConsumos.stream()
                .filter(c -> c.getEstado().equals(SUCCESS))
                .collect(Collectors.toList())
                .size()
        );
        assertEquals(1, allConsumos.stream()
                .filter(c -> c.getEstado().equals(FAILURE.toString()))
                .collect(Collectors.toList())
                .size()
        );

        // asserts for logs consumo db and results
        assertEquals(0, consumoJobManager.getMsConsumoResultDao().select(null).size());
    }

    @Test
    public void test_22() throws Exception {
        // Case: 3 concesionaria approved with service available return empty list for notification updates
        consumoJobScenarios.setConcesionaria(AXIS, true);
        consumoJobScenarios.setConcesionaria(REST, true);
        consumoJobScenarios.setConcesionaria(CXF, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(AXIS, "notifications_update_axis.json");
            put(REST, "notifications_update_rest.json");
            put(CXF, "notifications_update_cxf.json");
        }};


        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName));

        consumer.execute(null);

        // run job
        assertNotNull(consumoJobManager.getMsJobConsumoDao().getLastJob());
        assertEquals(1, consumoJobManager.getMsJobConsumoDao().select().size());

        // asserts on consumo
        List<ConsumoForm> allConsumos = consumoJobManager.getMsConsumoDao().select(null);
        assertEquals(3, allConsumos.size());

        assertEquals(3, allConsumos.stream()
                .filter(c -> c.getEstado().equals(SUCCESS))
                .collect(Collectors.toList())
                .size()
        );

        // asserts for logs consumo db and results
        assertEquals(18, consumoJobManager.getMsConsumoResultDao().select(null).size());
        assertTrue(consumoJobManager.getMsConsumoResultDao().select(null)
                .stream()
                .allMatch(c -> c.getResult().equals(SUCCESS))
        );
    }
}
