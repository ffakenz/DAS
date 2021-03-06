package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void test_12_Approve_concesionaria_ok() throws SQLException {

        final DynaActionForm concesionariaForm = new DynaActionForm();
        concesionariaForm.setItem("id", "1");

        final InteractorResponse<Boolean> result = aprobarInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());

        // validate if the concesionaria was really updated
        final List<ConcesionariaForm> concesionariaFormsApproved = msConcesionariasDao.selectAprobadas();
        assertTrue(concesionariaFormsApproved.stream().anyMatch(c -> c.getId().equals(1L)));
    }

    @Test()
    public void test_13_Approve_unexistent_concesionaria_fail() throws SQLException {

        final DynaActionForm concesionariaForm = new DynaActionForm();
        concesionariaForm.setItem("id", "10");

        final InteractorResponse<Boolean> result = aprobarInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.FAILURE, result.getResponse());
        assertFalse(result.getResult());
    }

    @Test()
    public void test_14_Approve_concesionaria_without_id_fail() throws SQLException {

        final DynaActionForm concesionariaForm = new DynaActionForm();

        final InteractorResponse<Boolean> result = aprobarInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.WARNING, result.getResponse());
        assertFalse(result.getResult());
    }

    @Test()
    public void test_15_Approve_concesionaria_twice_ok() throws SQLException {

        final DynaActionForm concesionariaForm = new DynaActionForm();
        concesionariaForm.setItem("id", "1");

        InteractorResponse<Boolean> result = aprobarInteractor.execute(concesionariaForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());

        // validate if the concesionaria was really updated
        List<ConcesionariaForm> concesionariaFormsApproved = msConcesionariasDao.selectAprobadas();
        assertTrue(concesionariaFormsApproved.stream().anyMatch(c -> c.getId().equals(1L)));

        final Timestamp dateFirstUpdate = concesionariaFormsApproved
                .stream()
                .filter(c -> c.getId().equals(1L))
                .findFirst()
                .get()
                .getFechaAlta();

        // try to approve a concesionaria that was previousley approved
        result = aprobarInteractor.execute(concesionariaForm);

        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());

        concesionariaFormsApproved = msConcesionariasDao.selectAprobadas();

        final Timestamp dateSecondUpdate = concesionariaFormsApproved
                .stream()
                .filter(c -> c.getId().equals(1L))
                .findFirst()
                .get()
                .getFechaAlta();

        // si son iguales significa que se mantuvo como fecha de aprobación la original
        assertEquals(dateFirstUpdate, dateSecondUpdate);
    }
}
