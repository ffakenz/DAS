package ar.edu.ubp.das.src.estado_cuentas;

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
import java.util.Optional;

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
        cuotaForm.setNroCuota(4L);

        final List<CuotasForm> cuotas = cuotasDao.selectByEstadoCuenta(cuotaForm);

        assertEquals(3, cuotas.size());

        cuotaForm.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        cuotaForm.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuotaForm.setMonto(null);
        cuotaForm.setFechaPago(null);

        cuotasDao.insert(cuotaForm);

        final List<CuotasForm> cuotas2 = cuotasDao.selectByEstadoCuenta(cuotaForm);
        // hay una cuota mas
        assertEquals(4, cuotas2.size());

        assertTrue(cuotas2.contains(cuotaForm));
    }

    @Test
    public void test_12_Validar_select_cuota_successfully() throws SQLException {

        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(1L);
        cuotaForm.setNroCuota(1L);

        final Optional<CuotasForm> cuotas = cuotasDao.selectCuota(cuotaForm);

        assertTrue(cuotas.isPresent());

        final CuotasForm cuota = cuotas.get();
        assertEquals(Timestamp.valueOf("2018-01-01 21:58:01"), cuota.getFechaAltaConcesionaria());
        assertEquals(Timestamp.valueOf("2018-02-01 21:58:01"), cuota.getFechaVencimiento());
        assertEquals(Integer.valueOf(10000), cuota.getMonto());
        assertEquals(Timestamp.valueOf("2018-01-27 21:58:01"), cuota.getFechaPago());
    }

    @Test
    public void test_13_Validar_pagar_cuota_successfully() throws SQLException {
        // Create new cuota
        final CuotasForm cuotaForm = new CuotasForm();
        cuotaForm.setEstadoCuentaId(1L);
        cuotaForm.setNroCuota(4L);

        final List<CuotasForm> cuotas = cuotasDao.selectByEstadoCuenta(cuotaForm);

        assertEquals(3, cuotas.size());

        cuotaForm.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        cuotaForm.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuotaForm.setMonto(null);
        cuotaForm.setFechaPago(null);

        cuotasDao.insert(cuotaForm);

        cuotaForm.setFechaVencimiento(Timestamp.valueOf("2018-02-01 21:58:01"));
        cuotaForm.setMonto(10000);
        cuotaForm.setFechaPago(Timestamp.valueOf("2018-06-30 21:58:01"));

        cuotasDao.pagarCuota(cuotaForm);

        final List<CuotasForm> cuotas2 = cuotasDao.selectByEstadoCuenta(cuotaForm);
        // hay una cuota mas
        assertEquals(4, cuotas2.size());

        assertTrue(cuotas2.contains(cuotaForm));
    }


}
