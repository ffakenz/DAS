package concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

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
        configurarConcesionariaForm.setConfigTecno("REST");
        configurarConcesionariaForm.setConfigParam("url");
        configurarConcesionariaForm.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        final int cantConfiguraciones = dao.select().size();

        dao.insert(configurarConcesionariaForm);

        assertEquals(cantConfiguraciones + 1, dao.select().size());
    }

    @Test(expected = SQLException.class)
    public void test_03_Insert_config_fail() throws SQLException {

        configurarConcesionariaForm.setConcesionariaId(4l);
        configurarConcesionariaForm.setConfigTecno("REST");
        configurarConcesionariaForm.setConfigParam("asd");
        configurarConcesionariaForm.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        dao.insert(configurarConcesionariaForm);
    }
}
