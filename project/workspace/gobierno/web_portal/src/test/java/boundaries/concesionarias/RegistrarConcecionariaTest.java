package boundaries.concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.boundaries.Registrar;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrarConcecionariaTest {

    MSConcesionariasDao dao;
    DatasourceConfig dataSourceConfig;

    @Before
    public void setup() throws SQLException {
        dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        dao = new MSConcesionariasDao();
        dao.setDatasource(dataSourceConfig);
    }


    @Test
    public void test01MockDBIsEmpty() throws SQLException {

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");

        assertEquals(false, dao.select(null).contains(concecionaria));
    }

    @Test
    public void test02RegistrarConcecionarias() throws SQLException {

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");
        concecionaria.setDireccion("dire123");
        concecionaria.setTel("123123");
        concecionaria.setEmail("email@123.com");

        final Registrar concImpl = new RegistrarInteractor();

        final Optional<Long> concesionariaId = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        final Long expectedConcesionariaId = 7L;

        assertEquals(true, dao.select(null).stream().anyMatch(d -> {
            return ((ConcesionariaForm) d).getNombre().equals("C10");
        }));
        assertEquals(concesionariaId, Optional.of(expectedConcesionariaId));
    }

    @Test
    public void test03RegistrarTwice() {

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C11");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT11");
        concecionaria.setDireccion("dire123");
        concecionaria.setTel("123123");
        concecionaria.setEmail("email@123.com");

        final Registrar concImpl = new RegistrarInteractor();
        final Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);
        final Optional<Long> logInId2 = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(logInId, Optional.of(new Long(6)));
        assertEquals(logInId2, Optional.empty());
    }
}

