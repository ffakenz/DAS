package interactors.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.daos.extenders.MSLoginDaoEx;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginInteractorTest {

    MSLogInDao loginDao;
    MSUsuariosDao msUsuariosDao;
    LoginInteractor interactor;

    @Before
    public void setup() {
        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        loginDao = new MSLogInDao();
        loginDao.setDatasource(dataSourceConfig);

        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

        interactor = new LoginInteractor(loginDao, msUsuariosDao);
    }

    @Test
    public void test01VerifyUserFailedWithWrongUsername() throws SQLException {
        final UsuarioForm userMock = new UsuarioForm("WrongUsername", "123", "gobierno");
        final Boolean isUsuarioValido = interactor.isValidUsuario(userMock);
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test02VerifyUserFailedWithWrongPassword() throws SQLException {
        final UsuarioForm userMock = new UsuarioForm("ffakenz", "WrongPassword", "gobierno");
        final Boolean isUsuarioValido = interactor.isValidUsuario(userMock);
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test03VerifyUserSuccessfully() throws SQLException {
        final UsuarioForm userMock = new UsuarioForm("ffakenz", "123", "gobierno");
        final Boolean isUsuarioValido = interactor.isValidUsuario(userMock);
        assertTrue(isUsuarioValido);
    }

    @Test
    public void test04VerifyIsUserLoggedInFalse() throws SQLException {
        final LogInForm loginRqst = new LogInForm("irocca");
        final Optional<Long> loginId = interactor.isLoggedIn(loginRqst);
        assertEquals(Optional.empty(), loginId);
    }

    @Test
    public void test05VerifyIsUserLoggedInTrue() throws SQLException {
        final LogInForm loginRqst = new LogInForm("ffakenz");
        final Optional<Long> loginId = interactor.isLoggedIn(loginRqst);
        assertEquals(Optional.of(2L), loginId);
    }

    @Test
    public void test06VerifyLogoutALoginUser() throws SQLException {
        final LogInForm loginRqst = new LogInForm("ffakenz");
        loginRqst.setId(2L);

        // VERIFY THE USER IS LOGGED IN
        final Optional<Long> loggedIn = interactor.isLoggedIn(loginRqst);
        assertEquals(Optional.of(2L), loggedIn);

        // LOGOUT THE USER
        interactor.logout(loginRqst);

        // VERIFY THE USER IS LOGGED OUT
        final Optional<Long> loggedOut = interactor.isLoggedIn(loginRqst);
        assertEquals(Optional.empty(), loggedOut);
    }

    @Test
    public void test07VerifyLogoutAnAlreadyLoggedOutUserShouldNotTakeEffect() throws SQLException {
        final LogInForm loginRqst = new LogInForm("ffakenz");
        loginRqst.setId(2L);

        // VERIFY THE USER IS LOGGED OUT
        final Optional<Long> loggedOut = interactor.isLoggedIn(loginRqst);
        assertEquals(Optional.empty(), loggedOut);

        // GET THE LAST LOGOUT TIME FOR THE USER
        final List<LogInForm> logoutDate = new MSLoginDaoEx(loginDao).selectLastUserLogin(loginRqst);

        // LOGOUT THE USER
        interactor.logout(loginRqst);

        // GET THE LAST LOGOUT TIME FOR THE USER
        final List<LogInForm> logoutDate2 = new MSLoginDaoEx(loginDao).selectLastUserLogin(loginRqst);

        // VERIFY THE DATE DID NOT CHANGED
        assertEquals(logoutDate.toString(), logoutDate2.toString());
    }


    @Test
    public void test08LoginSuccessfully2() throws SQLException {

        final LogInForm loginRqst = new LogInForm("pepe");
        final Optional<Long> logInId = interactor.login(loginRqst);

        assertEquals(Optional.of(3L), logInId);
    }


    @Test
    public void test09LoginSuccessfully() throws SQLException {
        // create a login request
        final LogInForm logRqst = new LogInForm("pepe");
        // verify the user is not logged in
        assertEquals(Optional.of(3L), interactor.isLoggedIn(logRqst));
        // log out using same request ?
        logRqst.setId(3L);
        interactor.logout(logRqst);
        // verify the user is not logged in
        assertEquals(Optional.empty(), interactor.isLoggedIn(logRqst));
    }


    // EXECUTE
    @Test
    public void test10LoginInteractor() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "asd");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertEquals(4L, response.getResult());
    }

    @Test
    public void test11LoginTwice() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "asd");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertEquals(5L, response.getResult());


        final InteractorResponse response2 = interactor.execute(userForm);
        assertEquals(ResponseForward.SUCCESS, response2.getResponse());
        assertEquals(6L, response2.getResult());
    }

    @Test
    public void test12MissingCredentials() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");

        final InteractorResponse response = interactor.execute(userForm);

        assertEquals(ResponseForward.WARNING, response.getResponse());
        assertEquals(Optional.empty(), response.getResult());
    }

    @Test
    public void test13LoginFailure() {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "no exists");
        userForm.setItem("password", "no password");

        final InteractorResponse response = interactor.execute(userForm);

        assert (response.getResponse() == ResponseForward.FAILURE);
        assert (((Optional<Long>) response.getResult()).equals(Optional.empty()));
    }
}
