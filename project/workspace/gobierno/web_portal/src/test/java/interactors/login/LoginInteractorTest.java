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
import org.junit.Ignore;
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
        assertEquals(logoutDate, logoutDate2);
    }


    @Test
    public void test08LoginSuccessfully2() throws SQLException {
        final LoginInteractor interactor = new LoginInteractor(loginDao, msUsuariosDao);

        final LogInForm loginRqst = new LogInForm("pepe");
        final Optional<Long> logInId = interactor.login(loginRqst);

        assertEquals(Optional.of(new Long(3)), logInId);
    }


    @Ignore
    public void test01LoginSuccessfully() throws SQLException {
        final LoginInteractor interactor = new LoginInteractor(loginDao, msUsuariosDao);
        // create a login request
        final LogInForm logRqst = new LogInForm("pepe");
        // try to login the user
        final Optional<Long> logInId = interactor.login(logRqst);
        // verify the user is not logged in
        assert (!interactor.isLoggedIn(logRqst).equals(Optional.empty()));
        // verify he has a login id
        assert (logInId.equals(Optional.of(new Long(1))));
        // log out using same request ?
        interactor.logout(logRqst);
        // verify the user is not logged in
        assert (interactor.isLoggedIn(logRqst).equals(Optional.empty()));
    }


    // EXECUTE
    @Ignore
    public void test05LoginInteractor() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "123");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.SUCCESS);
        assert (((Optional<Long>) response.getResult()).orElse(Long.MIN_VALUE) == 1);
    }

    @Ignore
    public void test06LoginTwice() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "123");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.SUCCESS);
        assert (((Optional<Long>) response.getResult()).orElse(Long.MIN_VALUE) == 1);


        final InteractorResponse response2 = action.execute(userForm);
        assert (response2.getResponse() == ResponseForward.SUCCESS);
        assert (((Optional<Long>) response2.getResult()).orElse(Long.MIN_VALUE) == 2);
    }

    @Ignore
    public void test07MissingCredentials() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.WARNING);
        assert (((Optional<Long>) response.getResult()).equals(Optional.empty()));
    }

    @Ignore
    public void test08LoginFailure() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "no exists");
        userForm.setItem("password", "no password");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.FAILURE);
        assert (((Optional<Long>) response.getResult()).equals(Optional.empty()));
    }
}
