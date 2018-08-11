package interactors.login;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioManager;
import ar.edu.ubp.das.src.login.model.usuario.UsuarioRol;
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
    public void test08() throws SQLException {
        // Verify the username does not exists
        final Boolean isUsuarioValido = usuarioManager.verifyUsername("WrongUsername");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test09() throws SQLException {
        // Verify the username exists
        final Boolean isUsuarioValido = usuarioManager.verifyUsername("ffakenz");
        assertTrue(isUsuarioValido);
    }

    @Test
    public void test10() throws SQLException {
        // Verify User Failed With Wrong Username
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("WrongUsername", "123");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test11() throws SQLException {
        // Verify User Failed With Wrong Password
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "WrongPassword");
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test12() throws SQLException {
        // Verify User Successfully
        final Boolean isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "123");
        assertTrue(isUsuarioValido);
    }

    @Test
    public void test13() throws SQLException {
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
    public void test14() throws SQLException {
        // Create a new user
        final String username = "newusername";

        // Delete the user
        usuarioManager.deleteUser(username);

        // Verify the user does exists anymore
        final Boolean isUsuarioValido = usuarioManager.verifyUsername(username);
        assertFalse(isUsuarioValido);
    }

    @Test
    public void test15() throws SQLException {
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
