package boundaries.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Optional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogOutTest {

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
    public void testLoginSuccessfully() {
        final LoginInteractor logueador = new LoginInteractor(loginDao, msUsuariosDao);
        // create a login request
        final LogInForm logRqst = new LogInForm("pepe");
        // try to login the user
        final Optional<Long> logInId = logueador.login(logRqst);
        // verify the user is logged in
        assert (!logueador.isLoggedIn(logRqst).equals(Optional.empty()));
        // verify he has a login id
        assert (logInId.equals(Optional.of(new Long(1))));
        // log out using same request ?
        logueador.logout(logRqst);
        // verify the user is not logged in
        assert (logueador.isLoggedIn(logRqst).equals(Optional.empty()));
    }
}
