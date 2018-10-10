package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.RegistrarConcesionariaInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegistrarConcesionariaInteractorTest {

    MSConcesionariasDao msConcesionariasDao;
    RegistrarConcesionariaInteractor registrarConcesionariaInteractor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

        registrarConcesionariaInteractor = new RegistrarConcesionariaInteractor(msConcesionariasDao);
    }

    @Test
    public void test12_Register_concesionaria_ok() throws SQLException {

        DynaActionForm concesionariaForm = new DynaActionForm();
        concesionariaForm.setItem("nombre", "concesionaria-test-1");
        concesionariaForm.setItem("direccion", "Direccion test 123");
        concesionariaForm.setItem("cuit", "cuit-test-123");
        concesionariaForm.setItem("tel", "tel-test-123");
        concesionariaForm.setItem("email", "test@test.com");

        // Insert the concesionaria
        InteractorResponse<Boolean> result = registrarConcesionariaInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());
    }

    @Test
    public void test12_Register_concesionaria_fail() throws SQLException {

        DynaActionForm concesionariaForm = new DynaActionForm();
        concesionariaForm.setItem("nombre", "concesionaria-test-1");
        concesionariaForm.setItem("direccion", "Direccion test 123");
        concesionariaForm.setItem("cuit", "cuit-test-123");
        concesionariaForm.setItem("tel", "tel-test-123");
        // concesionariaForm.setItem("email", "test@test.com");

        // Insert the concesionaria
        InteractorResponse<Boolean> result = registrarConcesionariaInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.WARNING, result.getResponse());
        assertFalse(result.getResult());
    }
}
