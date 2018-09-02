package daos.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstadoCuentasTest {

    MSEstadoCuentasDao estadoCuentaDao;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        estadoCuentaDao = new MSEstadoCuentasDao();
        estadoCuentaDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test_09_Consultar_estado_cuentas_failed() throws SQLException {

        final EstadoCuentasForm estadoCuentaForm = new EstadoCuentasForm();
        estadoCuentaForm.setConcesionariaId(1L);
        estadoCuentaForm.setNroPlanConcesionaria(1L);

        final Optional<EstadoCuentasForm> ecs = estadoCuentaDao.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertFalse(ecs.isPresent());
    }

    @Test
    public void test_10_Consultar_estado_cuentas_successfully() throws SQLException {

        final EstadoCuentasForm estadoCuentaForm = new EstadoCuentasForm();
        estadoCuentaForm.setConcesionariaId(1L);
        estadoCuentaForm.setNroPlanConcesionaria(1001L);

        final Optional<EstadoCuentasForm> ecs = estadoCuentaDao.selectByNroPlanAndConcesionaria(estadoCuentaForm);
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

        estadoCuentaDao.insert(estadoCuentaForm);

        final Optional<EstadoCuentasForm> ecs = estadoCuentaDao.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertTrue(ecs.isPresent());
    }

    @Test
    public void test_12_Validate_actualizar_estado_cuenta_successfully() throws SQLException {

        final EstadoCuentasForm estadoCuentaForm = new EstadoCuentasForm();
        estadoCuentaForm.setConcesionariaId(1L);
        estadoCuentaForm.setNroPlanConcesionaria(1001L);

        // Verify the plan exists
        final Optional<EstadoCuentasForm> ecs = estadoCuentaDao.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertTrue(ecs.isPresent());

        // Update estado plan
        estadoCuentaForm.setEstado("en_proceso");
        estadoCuentaDao.update(estadoCuentaForm);

        // Verify the plan got successfully updated
        final Optional<EstadoCuentasForm> ecsUpdated = estadoCuentaDao.selectByNroPlanAndConcesionaria(estadoCuentaForm);
        assertEquals("en_proceso", ecsUpdated.get().getEstado());

    }
}
