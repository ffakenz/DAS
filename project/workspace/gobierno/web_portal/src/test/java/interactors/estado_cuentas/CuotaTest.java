package interactors.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
import ar.edu.ubp.das.src.estado_cuentas.model.CuotasManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CuotaTest {

    MSCuotasDao cuotasDao;
    CuotasManager cuotasManager;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        cuotasDao = new MSCuotasDao();
        cuotasDao.setDatasource(dataSourceConfig);

        cuotasManager = new CuotasManager(cuotasDao);
    }

    @Test
    public void test_08_Consultar_all_cuotas_successfully() throws SQLException {

        final List<CuotasForm> cuotas = cuotasManager.selectAll();
        assertEquals(12, cuotas.size());
    }

    @Test
    public void test_09_Consultar_cuotas_by_fail_estado_cuenta_id() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(5L);

        final List<CuotasForm> cuotas = cuotasManager.selectByEstadoCuenta(cuotaForm);
        assertTrue(cuotas.isEmpty());
    }

    @Test
    public void test_10_Consultar_cuotas_by_estado_cuenta_id_successfully() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(4L);

        final List<CuotasForm> cuotas = cuotasManager.selectByEstadoCuenta(cuotaForm);

        assertFalse(cuotas.isEmpty());
        assertEquals(3, cuotas.size());
    }

    @Test
    public void test_11_Validar_registrar_successfully() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(1L);

        List<CuotasForm> cuotas = cuotasManager.selectByEstadoCuenta(cuotaForm);

        assertEquals(3, cuotas.size());

        cuotaForm.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuotaForm.setMonto(10000);
        cuotaForm.setFechaPago(Timestamp.valueOf("2018-06-30 21:58:01"));

        cuotasManager.insert(cuotaForm);

        cuotas = cuotasManager.selectByEstadoCuenta(cuotaForm);
        // hay una cuota mas
        assertEquals(4, cuotas.size());

        cuotaForm.setId(13L);
        assertTrue(cuotas.contains(cuotaForm));
    }
}
