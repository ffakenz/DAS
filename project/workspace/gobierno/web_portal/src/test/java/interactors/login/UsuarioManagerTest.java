package interactors.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioManager;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioRol;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioManagerTest {
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
    public void test08_Verify_the_username_does_not_exists() throws SQLException {
        final Boolean isUsuarioValido = usuarioManager.verifyUsername("WrongUsername");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test09_Verify_the_username_exists() throws SQLException {
        final Boolean isUsuarioValido = usuarioManager.verifyUsername("ffakenz");
        assertTrue(isUsuarioValido);
    }

    @Test
    public void test10_Verify_User_Failed_With_Wrong_Username() throws SQLException {
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("WrongUsername", "123");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test11_Verify_User_Failed_With_Wrong_Password() throws SQLException {
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "WrongPassword");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test12_Verify_User_Successfully() throws SQLException {
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "123");
        assertTrue(isUsuarioValido);
    }

    @Test
    public void test13_New_User_Exists() throws SQLException {
        // Create a new user
        final String username = "newusername";
        final String password = "newpassword";
        final UsuarioRol rol = UsuarioRol.GOBIERNO;
        usuarioManager.createUser(username, password, rol);

        // Verify theu user exists
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword(username, password);
        assertTrue(isUsuarioValido);
    }

    @Test
    public void test14_Verify_The_User_Is_Successfully_Deleted() throws SQLException {
        final String username = "newusername";

        // Delete the user
        usuarioManager.deleteUser(username);

        // Verify the user does exists anymore
        final Boolean isUsuarioValido = usuarioManager.verifyUsername(username);
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test15_Verify_The_User_Update_His_Password_Successfully() throws SQLException {
        final String username = "ffakenz";
        final String password = "123";

        // Verify the username and passwords exists
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword(username, password);
        assertTrue(isUsuarioValido);

        // Update the users password
        final String newPassword = "newPassword";
        usuarioManager.updatePassword(username, newPassword);

        // Verify the old password is wrong
        final Boolean isOldPasswordValid = usuarioManager.verifyUsernameAndPassword(username, password);
        assertFalse(isOldPasswordValid);

        // Verify the new password is correct
        final Boolean isNewPasswordValid = usuarioManager.verifyUsernameAndPassword(username, newPassword);
        assertTrue(isNewPasswordValid);
    }


}
