package boundaries.login;

import login.LoginInteractor;
import login.forms.UsuarioForm;
import mocks.MSUsuariosDaoMock;
import org.junit.Test;
import static org.junit.Assert.*;
public class ValidarUsuarioTest {

    @Test
    public void testValidateSuccessfully() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("pepe");
        userMock.setPassword("123");
        userMock.setRol("gobierno");

        Boolean isUsuarioValido =  new LoginInteractor().validarUsuario(userMock).apply(daoMock);
        assertEquals(true, isUsuarioValido);
    }

    @Test
    public void testValidateFailed() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("lol");
        userMock.setPassword("123");
        userMock.setRol("gobierno");

        Boolean isUsuarioValido =  new LoginInteractor().validarUsuario(userMock).apply(daoMock);
        assertEquals(false, isUsuarioValido);
    }
}
