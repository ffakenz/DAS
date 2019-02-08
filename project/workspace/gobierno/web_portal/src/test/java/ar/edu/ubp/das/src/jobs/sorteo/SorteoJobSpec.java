package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;
import clients.factory.ClientFactory;
import clients.factory.ClientType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.ClientFactoryStub;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;
import util.scenarios.SorteoJobScenarios;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.COMPLETADO;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CONSUMO;
import static clients.factory.ClientType.CXF;
import static clients.factory.ClientType.REST;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SorteoJobSpec {


    private DatasourceConfig datasourceConfig;
    private SorteoJob sorteoJob;
    private SorteoJobManager sorteoJobManager;
    private SorteoJobScenarios sorteoJobScenarios;
    private ConsumoJobScenarios consumoJobScenarios;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        datasourceConfig = TestDB.getInstance().getDataSourceConfig();
        sorteoJobManager = new SorteoJobManager(datasourceConfig);
        MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        sorteoJobScenarios = new SorteoJobScenarios(datasourceConfig);
        consumoJobScenarios = new ConsumoJobScenarios(datasourceConfig);
    }

    @Test
    public void test_01() throws SQLException {
        // without sorteo , base case
        sorteoJob = new SorteoJob(datasourceConfig, new ClientFactoryStub(null), new SendEmailStubSuccess());
        sorteoJob.execute(null);

        assertEquals(0, sorteoJobManager.getMsSorteoDao().select().size());
    }

    @Test
    public void test_02() throws SQLException {
        // with sorteo nuevo , but not concesionarias approved
        sorteoJobScenarios.setSorteoNuevoParaHoy();

        sorteoJob = new SorteoJob(datasourceConfig, new ClientFactoryStub(null), new SendEmailStubSuccess());
        sorteoJob.execute(null);

        assertEquals(1, sorteoJobManager.getMsSorteoDao().select().size());

        Optional<SorteoForm> sorteoById = sorteoJobManager.getMsSorteoDao().getSorteoById(1L);
        assertTrue(sorteoById.isPresent());
        assertEquals(COMPLETADO, sorteoById.get().getEstadoSorteo());
    }

    @Test
    public void test_03() throws SQLException {
        // sorteo nuevo para hoy , con concesionarias aprobadas, pero sin ningun estado de cuenta que cumpla las condiciones
        sorteoJobScenarios.setSorteoNuevoParaHoy();
        // aprobamos una concesionaria rest
        sorteoJobScenarios.approveRestConcesionaria();

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "plan_bean_rest.json");
        }};

        sorteoJob = new SorteoJob(datasourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName), new SendEmailStubSuccess());
        sorteoJob.execute(null);

        // seteamos valores de cuotas para los que sabemos que no tenemos estados de cuenta que cumplan
        Constants.CUOTAS_MIN = 10;
        Constants.CUOTAS_MAX = 20;

        sorteoJob.execute(null);

        assertEquals(1, sorteoJobManager.getMsSorteoDao().select().size());
        Optional<SorteoForm> sorteoById = sorteoJobManager.getMsSorteoDao().getSorteoById(1L);
        assertTrue(sorteoById.isPresent());
        assertEquals(COMPLETADO, sorteoById.get().getEstadoSorteo());
    }

    @Test
    public void test_04() throws SQLException {
        // sorteo nuevo para hoy , con concesionaria aprobada, falla en consumo por service unavailable
        sorteoJobScenarios.setSorteoNuevoParaHoy();
        // aprobamos una concesionaria rest
        ConcesionariaForm concesionariaForm = consumoJobScenarios.setConcesionaria(REST, true);
        sorteoJobScenarios.setEstadoCuentaForConcesionaria(123L, concesionariaForm);

        sorteoJob = new SorteoJob(datasourceConfig, ClientFactory.getInstance(), new SendEmailStubSuccess());
        sorteoJob.execute(null);

        assertEquals(1, sorteoJobManager.getMsSorteoDao().select().size());
        Optional<SorteoForm> sorteoById = sorteoJobManager.getMsSorteoDao().getSorteoById(1L);
        assertTrue(sorteoById.isPresent());
        assertEquals(PENDIENTE_CONSUMO, sorteoById.get().getEstadoSorteo());
    }

    @Test
    public void test_05() throws SQLException {
        // sorteo nuevo para hoy, con concesionarias aprobadas, una falla , el resto success
        sorteoJobScenarios.setSorteoNuevoParaHoy();
        // aprobamos una concesionaria rest
        ConcesionariaForm concesionariaFormRest = consumoJobScenarios.setConcesionaria(REST, true);
        sorteoJobScenarios.setEstadoCuentaForConcesionaria(11111L, concesionariaFormRest);

        ConcesionariaForm concesionariaFormCxf = consumoJobScenarios.setConcesionaria(CXF, true);
        sorteoJobScenarios.setEstadoCuentaForConcesionaria(22222L,concesionariaFormCxf);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "plan_bean_rest.json");
        }};

        sorteoJob = new SorteoJob(datasourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName), new SendEmailStubSuccess());
        sorteoJob.execute(null);

        assertEquals(1, sorteoJobManager.getMsSorteoDao().select().size());
        Optional<SorteoForm> sorteoById = sorteoJobManager.getMsSorteoDao().getSorteoById(1L);
        assertTrue(sorteoById.isPresent());
        assertEquals(PENDIENTE_CONSUMO, sorteoById.get().getEstadoSorteo());
    }

    @Test
    public void test_06() throws SQLException {
        // sorteo nuevo para hoy, con concesionarias aprobadas, todas success, sin participantes
        sorteoJobScenarios.setSorteoNuevoParaHoy();
        // aprobamos una concesionaria rest
        ConcesionariaForm concesionariaFormRest = consumoJobScenarios.setConcesionaria(REST, true);
        sorteoJobScenarios.setEstadoCuentaForConcesionaria(11111L, concesionariaFormRest);

        ConcesionariaForm concesionariaFormCxf = consumoJobScenarios.setConcesionaria(CXF, true);
        sorteoJobScenarios.setEstadoCuentaForConcesionaria(22222L,concesionariaFormCxf);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(REST, "plan_bean_rest.json");
            put(CXF, "plan_bean_cxf.json");
        }};

        sorteoJob = new SorteoJob(datasourceConfig, new ClientFactoryStub(concesionariasXnotificationFileName), new SendEmailStubSuccess());
        sorteoJob.execute(null);

        assertEquals(1, sorteoJobManager.getMsSorteoDao().select().size());
        Optional<SorteoForm> sorteoById = sorteoJobManager.getMsSorteoDao().getSorteoById(1L);
        assertTrue(sorteoById.isPresent());
        assertEquals(COMPLETADO, sorteoById.get().getEstadoSorteo());
    }

    
}
