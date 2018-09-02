package daos.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
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

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        cuotasDao = new MSCuotasDao();
        cuotasDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test_08_Consultar_all_cuotas_successfully() throws SQLException {

        final List<CuotasForm> cuotas = cuotasDao.select(null);
        assertEquals(12, cuotas.size());
    }

    @Test
    public void test_09_Consultar_cuotas_by_fail_estado_cuenta_id() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(5L);

        final List<CuotasForm> cuotas = cuotasDao.selectByEstadoCuenta(cuotaForm);
        assertTrue(cuotas.isEmpty());
    }

    @Test
    public void test_10_Consultar_cuotas_by_estado_cuenta_id_successfully() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(4L);

        final List<CuotasForm> cuotas = cuotasDao.selectByEstadoCuenta(cuotaForm);

        assertFalse(cuotas.isEmpty());
        assertEquals(3, cuotas.size());
    }

    @Test
    public void test_11_Validar_registrar_successfully() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(1L);

        final List<CuotasForm> cuotas = cuotasDao.selectByEstadoCuenta(cuotaForm);

        assertEquals(3, cuotas.size());

        cuotaForm.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuotaForm.setMonto(10000);
        cuotaForm.setFechaPago(Timestamp.valueOf("2018-06-30 21:58:01"));

        cuotasDao.insert(cuotaForm);

        final List<CuotasForm> cuotas2 = cuotasDao.selectByEstadoCuenta(cuotaForm);
        // hay una cuota mas
        assertEquals(4, cuotas2.size());

        cuotaForm.setId(13L);
        cuotaForm.setNroCuota(4L);
        assertTrue(cuotas2.contains(cuotaForm));
    }
}
