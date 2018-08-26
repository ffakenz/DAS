package interactors.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.model.EstadoCuentasManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstadoCuentasTest {

    MSEstadoCuentasDao estadoCuentaDao;
    EstadoCuentasManager estadoCuentasManager;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        estadoCuentaDao = new MSEstadoCuentasDao();
        estadoCuentaDao.setDatasource(dataSourceConfig);

        estadoCuentasManager = new EstadoCuentasManager(estadoCuentaDao);
    }

    @Test
    public void test_09_Consultar_estado_cuentas_failed() throws SQLException {

        final EstadoCuentasForm estadoCuentaForm = new EstadoCuentasForm();
        estadoCuentaForm.setConcesionariaId(1L);
        estadoCuentaForm.setNroPlanConcesionaria(1L);

        final Optional<EstadoCuentasForm> ecs = estadoCuentasManager.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertFalse(ecs.isPresent());
    }

    @Test
    public void test_10_Consultar_estado_cuentas_successfully() throws SQLException {

        final EstadoCuentasForm estadoCuentaForm = new EstadoCuentasForm();
        estadoCuentaForm.setConcesionariaId(1L);
        estadoCuentaForm.setNroPlanConcesionaria(1001L);

        final Optional<EstadoCuentasForm> ecs = estadoCuentasManager.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertTrue(ecs.isPresent());
    }


    @Test
    public void test_11_Validar_registrar_successfully() throws SQLException {

        final EstadoCuentasForm estadoCuentaForm = new EstadoCuentasForm();
        estadoCuentaForm.setConcesionariaId(1L);
        estadoCuentaForm.setNroPlanConcesionaria(2001L);
        estadoCuentaForm.setDocumentoCliente(111L);
        estadoCuentaForm.setVehiculo(1L);
        estadoCuentaForm.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        estadoCuentaForm.setEstado("inicial");

        estadoCuentasManager.insert(estadoCuentaForm);

        final Optional<EstadoCuentasForm> ecs = estadoCuentasManager.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertTrue(ecs.isPresent());
    }

    @Test
    public void test_12_Validate_actualizar_estado_cuenta_successfully() throws SQLException {

        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(3));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        estadoCuenta.setEstado("inicial");

        estadoCuentasManager.insert(estadoCuenta);

        Optional<EstadoCuentasForm> ecs = estadoCuentasManager.selectByNroPlanAndConcesionaria(estadoCuenta);

        final Timestamp tiempo1 = ecs.get().getFechaUltimaActualizacion();

        estadoCuentasManager.update(ecs.get());

        ecs = estadoCuentasManager.selectByNroPlanAndConcesionaria(estadoCuenta);

        final Timestamp tiempo2 = ecs.get().getFechaUltimaActualizacion();

        assert (tiempo1.before(tiempo2));

    }
}
