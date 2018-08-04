package boundaries.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidarUsuarioTest {

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
    public void testValidateSuccessfully() {
        final UsuarioForm userMock = new UsuarioForm("pepe", "123", "gobierno");

        final Boolean isUsuarioValido = new LoginInteractor(loginDao, msUsuariosDao).validarUsuario(userMock);
        assertEquals(true, isUsuarioValido);
    }

    @Test
    public void testValidateFailed() {

        final UsuarioForm userMock = new UsuarioForm("lol", "123", "gobierno");
        final Boolean isUsuarioValido = new LoginInteractor(loginDao, msUsuariosDao).validarUsuario(userMock);
        assertEquals(false, isUsuarioValido);
    }
}
