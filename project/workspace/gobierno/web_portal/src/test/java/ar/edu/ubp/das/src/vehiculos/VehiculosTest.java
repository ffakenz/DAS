package ar.edu.ubp.das.src.vehiculos;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.vehiculos.daos.MSVehiculoDao;
import ar.edu.ubp.das.src.vehiculos.forms.VehiculoForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehiculosTest {

    MSVehiculoDao msVehiculoDao;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msVehiculoDao = new MSVehiculoDao();
        msVehiculoDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test_10_Validate_vehiculos_exists() throws SQLException {

        final ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(777L);
        consumerForm.setConcesionaria(1L);

        final List<VehiculoForm> vehiculos =
                msVehiculoDao.select();

        assertFalse(vehiculos.isEmpty());

        vehiculos.forEach(System.out::println);
    }
}
