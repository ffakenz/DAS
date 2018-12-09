package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.REST;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigurarConcesionariaTest {

    MSConfigurarConcesionariaDao dao;
    ConfigurarConcesionariaForm configurarConcesionariaForm;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        dao = new MSConfigurarConcesionariaDao();
        dao.setDatasource(dataSourceConfig);

        configurarConcesionariaForm = new ConfigurarConcesionariaForm();
    }


    @Test
    public void test_01_Get_all_configs() throws SQLException {
        final int cantConfiguraciones = dao.select().size();
        assertEquals(6, cantConfiguraciones);
    }

    @Test
    public void test_02_Insert_config_ok() throws SQLException {

        configurarConcesionariaForm.setConcesionariaId(4l);
        configurarConcesionariaForm.setConfigTecno(REST);
        configurarConcesionariaForm.setConfigParam("url");
        configurarConcesionariaForm.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        final int cantConfiguraciones = dao.select().size();

        dao.insert(configurarConcesionariaForm);

        assertEquals(cantConfiguraciones + 1, dao.select().size());
    }

    @Test(expected = SQLException.class)
    public void test_03_Insert_config_fail() throws SQLException {

        configurarConcesionariaForm.setConcesionariaId(4l);
        configurarConcesionariaForm.setConfigTecno(REST);
        configurarConcesionariaForm.setConfigParam("asd");
        configurarConcesionariaForm.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        dao.insert(configurarConcesionariaForm);
    }

    @Test
    public void test_04_Select_by_concesionaria_id_fail() throws SQLException {
        final List<ConfigurarConcesionariaForm> configurarConcesionariaForms = dao.selectParamsByConcesionariaId(6L);
        assertTrue(configurarConcesionariaForms.isEmpty());
    }

    @Test
    public void test_04_Select_by_concesionaria_id_successfully() throws SQLException {
        final List<ConfigurarConcesionariaForm> configurarConcesionariaForms = dao.selectParamsByConcesionariaId(3L);
        assertEquals(2, configurarConcesionariaForms.size());
    }
}


