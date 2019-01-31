package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginInteractorTest {

    MSLogInDao loginDao;
    MSUsuariosDao msUsuariosDao;
    LoginInteractor interactor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        loginDao = new MSLogInDao();
        loginDao.setDatasource(dataSourceConfig);

        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

        interactor = new LoginInteractor(loginDao, msUsuariosDao);
    }

    @Test
    public void test08_Login_Failure() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "no exists");
        userForm.setItem("password", "no password");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.FAILURE, response.getResponse());
        assertNull(response.getResult());
    }

    @Test
    public void test09_Missing_Credentials() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe2");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.WARNING, response.getResponse());
        assertNull(response.getResult());
    }

    @Test
    public void test10_Login_Interactor() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "perez");

        final InteractorResponse<Long> response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertTrue(response.getResult() > 0);
    }

    @Test
    public void test11_Login_Twice() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "perez");

        final InteractorResponse<Long> response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertTrue(response.getResult() > 0);


        final InteractorResponse<Long> response2 = interactor.execute(userForm);
        assertEquals(ResponseForward.SUCCESS, response2.getResponse());
        assertTrue(response2.getResult() > response.getResult());
    }
}
