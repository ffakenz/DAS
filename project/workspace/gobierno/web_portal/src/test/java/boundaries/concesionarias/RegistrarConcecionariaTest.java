package boundaries.concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.boundaries.Registrar;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ModuleConfigImpl.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrarConcecionariaTest {

    MSConcesionariasDao dao;
    DatasourceConfig dataSourceConfig;

    @Before
    public void setup() {

        dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        dao = new MSConcesionariasDao();
        dao.setDatasource(dataSourceConfig);

        //mockStatic(ModuleConfigImpl.class);
        Mockito.when(ModuleConfigImpl.getDatasourceById(anyString())).thenReturn(dataSourceConfig);
    }

    @Test
    public void test01RegistrarConcecionarias() throws SQLException {

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");
        concecionaria.setDireccion("dire123");
        concecionaria.setTel("123123");
        concecionaria.setEmail("email@123.com");

        final Registrar concImpl = new RegistrarInteractor();

        final Optional<Long> concesionariaId = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        final Long expectedConcesionariaId = 6L;

        assertEquals(true, dao.select(null).stream().anyMatch(d -> {
            return ((ConcesionariaForm) d).getNombre().equals("C10");
        }));
        assertEquals(Optional.of(expectedConcesionariaId), concesionariaId);
    }

    @Test
    public void test02RegistrarTwice() {

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

        assertEquals(Optional.of(new Long(7)), logInId);
        assertEquals(Optional.empty(), logInId2);
    }
}

