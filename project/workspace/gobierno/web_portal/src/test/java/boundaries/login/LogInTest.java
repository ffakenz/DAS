package boundaries.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LogInTest {

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
    public void testMockDBIsEmpty() throws SQLException {

        final LogInForm loginRqst = new LogInForm("pepe");

        assertEquals(false, loginDao.select(null).contains(loginRqst));
    }

    @Test
    public void testLoginSuccessfully() throws SQLException {

        final LogInForm loginRqst = new LogInForm("pepe");

        final LoginInteractor logueador = new LoginInteractor(loginDao, msUsuariosDao);
        final Optional<Long> logInId = logueador.login(loginRqst);

        assertEquals(true, loginDao.select(null).contains(loginRqst));
        assertEquals(logInId, Optional.of(new Long(1)));
        assert (logueador.isLoggedIn(loginRqst).equals(logInId));
    }

}
