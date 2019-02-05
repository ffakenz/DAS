package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;
import util.scenarios.ISorteoSpec;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static clients.factory.ClientType.AXIS;
import static clients.factory.ClientType.REST;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GetAprobadasDesactualizadasSpec implements ISorteoSpec {

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

        executeConsumoJob(dataSourceConfig, Timestamp.valueOf("2018-05-20 18:13:24"),
                pair(AXIS, "notifications_update_axis.json"),
                pair(REST, "notifications_update_rest.json"));

        executeConsumoJob(dataSourceConfig, null,
                pair(AXIS, "notifications_update_axis.json"));


        final List<ConcesionariaForm> concesionariaForms = msConcesionariasDao.selectAprobadasDesactualizadas(-5);
        assertFalse(concesionariaForms.contains(concesionariaForm1));
        assertTrue(concesionariaForms.contains(concesionariaForm2));
    }
}
