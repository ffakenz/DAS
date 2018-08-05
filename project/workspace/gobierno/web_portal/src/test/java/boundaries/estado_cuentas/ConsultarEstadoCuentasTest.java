package boundaries.estado_cuentas;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConsultarEstadoCuentasTest {

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
    public void testConsultarEstadoCuentasSuccessfully() throws SQLException {

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
}
