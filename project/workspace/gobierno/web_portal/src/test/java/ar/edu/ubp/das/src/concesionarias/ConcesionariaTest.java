package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConcesionariaTest {

    MSConcesionariasDao dao;
    ConcesionariaForm concesionariaForm;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        dao = new MSConcesionariasDao();
        dao.setDatasource(dataSourceConfig);

        concesionariaForm = new ConcesionariaForm();
    }


    @Test
    public void test_01_Aprobadas_is_empty() throws SQLException {
        assertEquals(0, dao.selectAprobadas().size());
    }

    @Test
    public void test_02_Aprobar_concesionaria() throws SQLException {

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

    @Test(expected = SQLException.class)
    public void test_03_Aprobar_concesionaria_fail() throws SQLException {

        final Optional<ConcesionariaForm> concesionaria = dao.select().stream().findFirst();

        assertNull(concesionaria.get().getFechaAlta());
        assertNotNull(concesionaria.get().getId());

        // try to approve concesionaria without code
        try {
            dao.approveConcesionaria(concesionaria.get());
        } catch (final SQLException e) {
            assertEquals("The parameter @codigo is null", e.getMessage());
            throw e;
        }
    }

    @Test
    public void test_04_Registrar_concesionaria_ok() throws SQLException {

        concesionariaForm.setNombre("nombre");
        concesionariaForm.setDireccion("direccion");
        concesionariaForm.setCuit("cuit");
        concesionariaForm.setTel("tel");
        concesionariaForm.setEmail("email");

        final Integer cantConcesionarias = dao.select().size();

        dao.insert(concesionariaForm);

        assertEquals(cantConcesionarias + 1, dao.select().size());
    }

    @Test(expected = SQLException.class)
    public void test_06_Registrar_twice() throws SQLException {

        concesionariaForm.setNombre("C11");
        concesionariaForm.setCuit("CUIT11");
        concesionariaForm.setDireccion("dire123");
        concesionariaForm.setTel("123123");
        concesionariaForm.setEmail("email@123.com");

        final Integer cantConcesionarias = dao.select().size();

        dao.insert(concesionariaForm);

        assertEquals(cantConcesionarias + 1, dao.select().size());

        // should be fail because try to insert the same concesionaria
        dao.insert(concesionariaForm);
    }

    @Test
    public void test_07_Disapprove_concesionaria() throws SQLException {
        concesionariaForm.setCodigo("codigo");
        concesionariaForm.setId(1L);

        final int cantAprobadas = dao.selectAprobadas().size();

        dao.approveConcesionaria(concesionariaForm);

        assertEquals(cantAprobadas + 1, dao.selectAprobadas().size());

        dao.disapproveConcesionaria(concesionariaForm);


        assertEquals(cantAprobadas, dao.selectAprobadas().size());
    }
}
