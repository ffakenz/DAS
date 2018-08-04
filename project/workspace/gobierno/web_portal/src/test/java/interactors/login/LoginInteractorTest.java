package interactors.login;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class LoginInteractorTest {

    MSLogInDao loginDao;
    MSUsuariosDao msUsuariosDao;

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
    }


    @Test
    public void testLoginInteractor() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "123");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.SUCCESS);
        assert (((Optional<Long>) response.getResult()).orElse(Long.MIN_VALUE) == 1);
    }

    @Test
    public void testLoginTwice() {
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

    @Test
    public void testMissingCredentials() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.WARNING);
        assert (((Optional<Long>) response.getResult()).equals(Optional.empty()));
    }

    @Test
    public void testLoginFailure() {
        final LoginInteractor action = new LoginInteractor(loginDao, msUsuariosDao);

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "no exists");
        userForm.setItem("password", "no password");

        final InteractorResponse response = action.execute(userForm);

        assert (response.getResponse() == ResponseForward.FAILURE);
        assert (((Optional<Long>) response.getResult()).equals(Optional.empty()));
    }
}
