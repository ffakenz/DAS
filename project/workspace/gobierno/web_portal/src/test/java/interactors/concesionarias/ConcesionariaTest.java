package interactors.concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.RegistrarInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConcesionariaTest {

    MSConcesionariasDao dao;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        dao = new MSConcesionariasDao();
        dao.setDatasource(dataSourceConfig);
    }


    @Test
    public void test01AprobadasIsEmpty() throws SQLException {
        assertEquals(0, dao.selectAprobadas().size());
    }

    @Test
    public void test02AprobarConcecionaria() throws SQLException {

        final ConcesionariaForm concesionariaForm = new ConcesionariaForm();
        concesionariaForm.setCodigo("codigo");
        concesionariaForm.setId(1L);

        final Optional<ConcesionariaForm> concesionariaForm1 = dao.selectByCodigo(concesionariaForm);
        assertFalse(concesionariaForm1.isPresent());

        dao.approveConcesionaria(concesionariaForm);

        final Optional<ConcesionariaForm> concesionariaForm2 = dao.selectByCodigo(concesionariaForm);

        assertEquals(concesionariaForm.getId(), concesionariaForm2.get().getId());
        assertNotNull(concesionariaForm2.get().getFechaAlta());
        assertEquals(1, dao.selectAprobadas().size());
    }

    @Ignore
    public void test01RegistrarConcecionarias() throws SQLException {

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C10");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT10");
        concecionaria.setDireccion("dire123");
        concecionaria.setTel("123123");
        concecionaria.setEmail("email@123.com");

        final RegistrarInteractor concImpl = new RegistrarInteractor();

        final Optional<Long> concesionariaId = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        final Long expectedConcesionariaId = 6L;

        assertEquals(true, dao.select(null).stream().anyMatch(d -> {
            return ((ConcesionariaForm) d).getNombre().equals("C10");
        }));
        assertEquals(Optional.of(expectedConcesionariaId), concesionariaId);
    }

    @Ignore
    public void test02RegistrarTwice() {

        final ConcesionariaForm concecionaria = new ConcesionariaForm();
        concecionaria.setNombre("C11");
        concecionaria.setConfig("AXIS");
        concecionaria.setCuit("CUIT11");
        concecionaria.setDireccion("dire123");
        concecionaria.setTel("123123");
        concecionaria.setEmail("email@123.com");

        final RegistrarInteractor concImpl = new RegistrarInteractor();
        final Optional<Long> logInId = concImpl.registrarConcesionaria(concecionaria).apply(dao);
        final Optional<Long> logInId2 = concImpl.registrarConcesionaria(concecionaria).apply(dao);

        assertEquals(Optional.of(new Long(7)), logInId);
        assertEquals(Optional.empty(), logInId2);
    }
}
