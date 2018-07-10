package boundaries.login;

import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import mocks.MSUsuariosDaoMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidarUsuarioTest {

    @Test
    public void testValidateSuccessfully() {
        final MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        final UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("pepe");
        userMock.setPassword("123");
        userMock.setRol("gobierno");

        final Boolean isUsuarioValido = new LoginInteractor().validarUsuario(userMock).apply(daoMock);
        assertEquals(true, isUsuarioValido);
    }

    @Test
    public void testValidateFailed() {
        final MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        final UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("lol");
        userMock.setPassword("123");
        userMock.setRol("gobierno");

        final Boolean isUsuarioValido = new LoginInteractor().validarUsuario(userMock).apply(daoMock);
        assertEquals(false, isUsuarioValido);
    }
}
