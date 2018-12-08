package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.SSID;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogoutInteractorTest {

    MSLogInDao loginDao;
    LogoutInteractor interactor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        loginDao = new MSLogInDao();
        loginDao.setDatasource(dataSourceConfig);

        interactor = new LogoutInteractor(loginDao);
    }

    @Test
    public void test10_Verify_logout_ok() throws SQLException {

        final DynaActionForm loginForm = new DynaActionForm();
        loginForm.setItem(SSID, "1");

        final InteractorResponse<Long> response = interactor.execute(loginForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
    }

    @Test
    public void test11_Verify_logout_without_username() throws SQLException {

        final DynaActionForm loginForm = new DynaActionForm();

        final InteractorResponse<Long> response = interactor.execute(loginForm);

        assertEquals(ResponseForward.WARNING, response.getResponse());
    }

    @Test
    public void test12_Verify_logout_user_loggedout() throws SQLException {

        final DynaActionForm loginForm = new DynaActionForm();
        loginForm.setItem(SSID, "1");


        final InteractorResponse<Long> response = interactor.execute(loginForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
    }

    @Test
    public void test13_Verify_logout_user_that_not_exists() throws SQLException {

        final DynaActionForm loginForm = new DynaActionForm();
        loginForm.setItem(SSID, "1");

        final InteractorResponse<Long> response = interactor.execute(loginForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
    }
}
