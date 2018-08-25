package interactors.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstadoCuentasTest {

    MSEstadoCuentaDao estadoCuentaDao;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        estadoCuentaDao = new MSEstadoCuentaDao();
        estadoCuentaDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test_10_Validate_actualizar_estado_cuenta_successfully() throws SQLException {


        final EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(3));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        estadoCuenta.setEstado("inicial");

        estadoCuentaDao.insert(estadoCuenta);

        List<EstadoCuentaForm> ecs = estadoCuentaDao.select(estadoCuenta);

        final Timestamp tiempo1 = ecs.get(0).getFechaUltimaActualizacion();

        estadoCuentaDao.update(ecs.get(0));

        ecs = estadoCuentaDao.select(estadoCuenta);

        final Timestamp tiempo2 = ecs.get(0).getFechaUltimaActualizacion();

        assert (tiempo1.before(tiempo2));

    }


    @Test
    public void test_Consultar_estado_cuentas_successfully() throws SQLException {

        final EstadoCuentaForm estadoCuentaMock = new EstadoCuentaForm();
        estadoCuentaMock.setConcesionariaId(Long.valueOf(6));
        estadoCuentaMock.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuentaMock.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuentaMock.setVehiculo(Long.valueOf(1));
        estadoCuentaMock.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        estadoCuentaMock.setEstado("inicial");

        estadoCuentaDao.insert(estadoCuentaMock);

        final List<EstadoCuentaForm> ecs = estadoCuentaDao.select(estadoCuentaMock);
        assertEquals(true, ecs.contains(estadoCuentaMock));
    }

    @Test
    public void test_Validar_registrar_successfully() throws SQLException {

        final EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        estadoCuentaDao.insert(estadoCuenta);

        assertEquals(true, estadoCuentaDao.select(null).contains(estadoCuenta));
    }
}
