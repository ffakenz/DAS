package interactors.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.model.login.LoginManager;
import ar.edu.ubp.das.src.login.model.login.MSLoginDaoEx;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginManagerTest {

    DaoImpl msloginDao;
    LoginManager loginManager;


    DaoImpl msUsuariosDao;
    UsuarioManager usuarioManager;
    LogInForm logInForm;

    @Before
    public void setup() throws SQLException {
        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        // Set up Login Manager
        msloginDao = new MSLogInDao();
        msloginDao.setDatasource(dataSourceConfig);
        loginManager = new LoginManager(msloginDao);

        // Set up Usuario Manager
        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);
        usuarioManager = new UsuarioManager(msUsuariosDao);

        final String testUsername = "pepe";
        logInForm = new LogInForm(testUsername);
    }

    @Test
    public void test03_Verify_Is_User_Logged_In_False() throws SQLException {
        final Optional<Long> loginId = loginManager.isLoggedIn(logInForm);
        assertFalse(loginId.isPresent());
    }

    @Test
    public void test04_Verify_Login_Successfully() throws SQLException {
        final Optional<Long> loginId = loginManager.login(logInForm);
        assertTrue(loginId.isPresent());
    }


    @Test
    public void test05_Verify_Is_User_Logged_In_True() throws SQLException {
        final Optional<Long> loginId = loginManager.isLoggedIn(logInForm);
        assertTrue(loginId.isPresent());
    }

    @Test
    public void test06_Verify_Logout_A_Login_User() throws SQLException {
        // VERIFY THE USER IS LOGGED IN
        final Optional<Long> loginId1 = loginManager.isLoggedIn(logInForm);
        assertTrue(loginId1.isPresent());
        logInForm.setId(loginId1.get());

        // LOGOUT THE USER
        loginManager.logout(logInForm);

        // VERIFY THE USER IS LOGGED OUT
        final Optional<Long> loginId2 = loginManager.isLoggedIn(logInForm);
        assertFalse(loginId2.isPresent());
    }

    @Ignore
    public void test07() throws SQLException {
        // Verify Logout An Already Logged Out User Should Not Take Effect

        // VERIFY THE USER IS LOGGED OUT
        final Optional<Long> loginId = loginManager.isLoggedIn(logInForm);
        assertFalse(loginId.isPresent());

        // GET THE LAST LOGOUT TIME FOR THE USER
        final Optional<Timestamp> logoutDate =
                new MSLoginDaoEx(msloginDao)
                        .selectLastUserLogin(logInForm)
                        .map(l -> l.getLogoutTime());

        // LOGOUT THE USER
        loginManager.logout(logInForm);

        // GET THE LAST LOGOUT TIME FOR THE USER
        final Optional<Timestamp> logoutDate2 =
                new MSLoginDaoEx(msloginDao)
                        .selectLastUserLogin(logInForm)
                        .map(l -> l.getLogoutTime());

        // VERIFY THE DATE DID NOT CHANGED
        assertEquals(logoutDate, logoutDate2);
    }
}
