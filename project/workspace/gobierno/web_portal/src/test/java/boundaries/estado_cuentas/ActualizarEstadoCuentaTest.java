package boundaries.estado_cuentas;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ActualizarEstadoCuentaTest {


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
    public void validateActualizarEstadoCuentaSuccessfully() throws InterruptedException, SQLException {


        final EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(3));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));
        estadoCuenta.setEstado("inicial");

        estadoCuentaDao.insert(estadoCuenta);

        List<DynaActionForm> ecs = estadoCuentaDao.select(estadoCuenta);

        final Timestamp tiempo1 = ((EstadoCuentaForm) ecs.get(0)).getFechaUltimaActualizacion();

        estadoCuentaDao.update(ecs.get(0));

        ecs = estadoCuentaDao.select(estadoCuenta);

        final Timestamp tiempo2 = ((EstadoCuentaForm) ecs.get(0)).getFechaUltimaActualizacion();

        assert (tiempo1.before(tiempo2));

    }
}
