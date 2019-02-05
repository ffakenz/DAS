package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.jobs.consumo.ConsumoJob;
import clients.factory.ClientType;
import org.junit.Before;
import org.junit.Test;
import util.ClentFactoryStub;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import static clients.factory.ClientType.AXIS;
import static clients.factory.ClientType.REST;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GetAprobadasDesactualizadasSpec {

    MSConcesionariasDao msConcesionariasDao;
    private ConsumoJobScenarios consumoJobScenarios;
    private DatasourceConfig dataSourceConfig;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDBGetAllAprobadasDesactualizadas();

        this.dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        consumoJobScenarios = new ConsumoJobScenarios(dataSourceConfig);

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

    }

    @Test
    public void test_00() throws SQLException {
        final ConcesionariaForm concesionariaForm1 = consumoJobScenarios.setConcesionaria(AXIS, true);
        final ConcesionariaForm concesionariaForm2 = consumoJobScenarios.setConcesionaria(REST, true);

        final HashMap concesionariasXnotificationFileName = new HashMap<ClientType, String>() {{
            put(AXIS, "notifications_update_axis.json");
            put(REST, "notifications_update_rest.json");
        }};
        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, new ClentFactoryStub(concesionariasXnotificationFileName), Timestamp.valueOf("2018-05-20 18:13:24"));
        consumer.execute(null);


        final HashMap concesionariasXnotificationFileName2 = new HashMap<ClientType, String>() {{
            put(AXIS, "notifications_update_axis_2.json");
        }};
        final ConsumoJob consumer2 = new ConsumoJob(dataSourceConfig, new ClentFactoryStub(concesionariasXnotificationFileName2));
        consumer2.execute(null);

        final List<ConcesionariaForm> concesionariaForms = msConcesionariasDao.selectAprobadasDesactualizadas(-5);
        System.out.println("concesionariaForm");
        System.out.println(concesionariaForm1);
        System.out.println("concesionariaForm2");
        System.out.println(concesionariaForm2);
        System.out.println("concesionariaForms");
        System.out.println(concesionariaForms);
        assertFalse(concesionariaForms.contains(concesionariaForm1));
        assertTrue(concesionariaForms.contains(concesionariaForm2));
    }
}
