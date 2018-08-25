package interactors.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

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
    public void test08_Login_Failure() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "no exists");
        userForm.setItem("password", "no password");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.FAILURE, response.getResponse());
        assertEquals(Optional.empty(), response.getResult());
    }

    @Test
    public void test09_Missing_Credentials() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe2");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.WARNING, response.getResponse());
        assertEquals(Optional.empty(), response.getResult());
    }

    @Test
    public void test10_Login_Interactor() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe2");
        userForm.setItem("password", "asd");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertTrue(response.getResult().isPresent());
    }

    @Test
    public void test11_Login_Twice() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe2");
        userForm.setItem("password", "asd");

        final InteractorResponse<Long> response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertTrue(response.getResult().isPresent());


        final InteractorResponse<Long> response2 = interactor.execute(userForm);
        assertEquals(ResponseForward.SUCCESS, response2.getResponse());
        assertTrue(response2.getResult().get() > response.getResult().get());
    }


}
