package ar.edu.ubp.das.src.usuarios;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import ar.edu.ubp.das.src.usuarios.model.UsuarioManager;
import ar.edu.ubp.das.src.usuarios.model.UsuarioRol;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioManagerTest {

    DaoImpl msUsuariosDao;
    UsuarioManager usuarioManager;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

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
        final Optional<UsuarioForm> isUsuarioValido = usuarioManager.verifyUsernameAndPassword("WrongUsername", "123");
        assertFalse(isUsuarioValido.isPresent());
    }

    @Test
    public void test11_Verify_User_Failed_With_Wrong_Password() throws SQLException {
        final Optional<UsuarioForm> isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "WrongPassword");
        assertFalse(isUsuarioValido.isPresent());
    }

    @Test
    public void test12_Verify_User_Successfully() throws SQLException {
        final Optional<UsuarioForm> isUsuarioValido = usuarioManager.verifyUsernameAndPassword("ffakenz", "123");
        assertTrue(isUsuarioValido.isPresent());
    }

    @Test
    public void test13_New_User_Successfully() throws SQLException {
        // Create a new user
        final String username = "newusername";
        final String password = "newpassword";

        final UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setUsername(username);
        usuarioForm.setPassword(password);
        usuarioForm.setRol(UsuarioRol.GOBIERNO.toString());

        usuarioManager.createUser(usuarioForm);

        // Verify the user exists
        final Optional<UsuarioForm> isUsuarioValido = usuarioManager.verifyUsernameAndPassword(username, password);
        assertTrue(isUsuarioValido.isPresent());
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
        final Optional<UsuarioForm> isUsuarioValido = usuarioManager.verifyUsernameAndPassword(username, password);
        assertTrue(isUsuarioValido.isPresent());

        // Update the users password
        final String newPassword = "newPassword";
        usuarioManager.updatePassword(username, newPassword);

        // Verify the old password is wrong
        final Optional<UsuarioForm> isOldPasswordValid = usuarioManager.verifyUsernameAndPassword(username, password);
        assertFalse(isOldPasswordValid.isPresent());

        // Verify the new password is correct
        final Optional<UsuarioForm> isNewPasswordValid = usuarioManager.verifyUsernameAndPassword(username, newPassword);
        assertTrue(isNewPasswordValid.isPresent());
    }


}
