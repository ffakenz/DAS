package ar.edu.ubp.das.src.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.model.LoginManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginManagerTest {

    DaoImpl msLoginDao;
    LoginManager loginManager;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msLoginDao = new MSLogInDao();
        msLoginDao.setDatasource(dataSourceConfig);

        loginManager = new LoginManager(msLoginDao);
    }

    @Test
    public void test10_Verify_is_logged_in_OK() throws SQLException {
        final Long doc = 222L;

        final LogInForm logInForm = new LogInForm();
        logInForm.setDocumento(doc);

        final Optional<Long> loggedIn = loginManager.isLoggedIn(logInForm);

        assertTrue(loggedIn.isPresent());
    }

    @Test
    public void test11_Verify_is_logged_in_Fail() throws SQLException {
        final Long doc = 111L;

        final LogInForm logInForm = new LogInForm();
        logInForm.setDocumento(doc);

        final Optional<Long> loggedIn = loginManager.isLoggedIn(logInForm);

        assertFalse(loggedIn.isPresent());
    }

    @Test
    public void test12_Verify_login_Ok() throws SQLException {
        final Long doc = 111L;

        final LogInForm logInForm = new LogInForm();
        logInForm.setDocumento(doc);

        final Optional<Long> login = loginManager.login(logInForm);

        assertTrue(login.isPresent());
    }

    @Test
    public void test13_Verify_login_twice_ok() throws SQLException {
        final Long doc = 111L;

        final LogInForm logInForm = new LogInForm();
        logInForm.setDocumento(doc);

        final Optional<Long> login = loginManager.login(logInForm);

        assertTrue(login.isPresent());

        final Optional<Long> login1 = loginManager.login(logInForm);

        assertTrue(login1.get() > login.get());
    }

    @Test
    public void test14_Verify_logout_an_already_user_loggedin_ok() throws SQLException {
        final Long id = 2L;

        final LogInForm logInForm = new LogInForm();
        logInForm.setId(id);

        loginManager.logout(logInForm);

        final Optional<Long> login = loginManager.isLoggedIn(logInForm);

        assertFalse(login.isPresent());
    }

    @Test
    public void test15_Verify_logout_an_already_user_loggedout_ok() throws SQLException {
        final Long id = 1L;

        final LogInForm logInForm = new LogInForm();
        logInForm.setId(id);

        loginManager.logout(logInForm);

        final Optional<Long> login = loginManager.isLoggedIn(logInForm);

        assertFalse(login.isPresent());
    }

}
