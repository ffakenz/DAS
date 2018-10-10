package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.AprobarInteractor;
import ar.edu.ubp.das.src.concesionarias.RegistrarConcesionariaInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AprobarInteractorTest {

    MSConcesionariasDao msConcesionariasDao;
    AprobarInteractor aprobarInteractor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

        aprobarInteractor = new AprobarInteractor(msConcesionariasDao);
    }

    @Test
    public void test12_Aprobar_concesionaria_ok() throws SQLException {

        DynaActionForm concesionariaForm = new DynaActionForm();
        concesionariaForm.setItem("id", "1");
        concesionariaForm.setItem("codigo", "codigo-test-1");

        InteractorResponse<Boolean> result = aprobarInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());
    }
}
