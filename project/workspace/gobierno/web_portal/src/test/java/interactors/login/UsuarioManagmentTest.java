package interactors.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioManager;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsuarioManagmentTest {
    DaoImpl msUsuariosDao;
    UsuarioManager usuarioManager;

    @Before
    public void setup() {
        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");
        
        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

        usuarioManager = new UsuarioManager(msUsuariosDao);
    }

    @Test
    public void test01() throws SQLException {
        // VerifyUserFailedWithWrongUsername
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("WrongUsername", "123");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test02() throws SQLException {
        // VerifyUserFailedWithWrongPassword
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "WrongPassword");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test03() throws SQLException {
        // VerifyUserSuccessfully
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "123");
        assertTrue(isUsuarioValido);
    }


}
