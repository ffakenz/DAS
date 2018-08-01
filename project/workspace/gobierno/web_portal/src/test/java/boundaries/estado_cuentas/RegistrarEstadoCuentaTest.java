package boundaries.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class RegistrarEstadoCuentaTest {

    MSEstadoCuentaDao estadoCuentaDao;
    DatasourceConfig dataSourceConfig;

    @Before
    public void setup() {
        dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        estadoCuentaDao = new MSEstadoCuentaDao();
        estadoCuentaDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void validarRegistrarSuccessfully() throws SQLException {

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