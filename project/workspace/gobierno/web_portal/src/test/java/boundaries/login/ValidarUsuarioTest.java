package boundaries.login;

import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidarUsuarioTest {

    MSUsuariosDao dao = new MSUsuariosDao();

    @Test
    public void testValidateSuccessfully() {
        final UsuarioForm userMock = new UsuarioForm("pepe", "123", "gobierno");

        final Boolean isUsuarioValido = new LoginInteractor().validarUsuario(userMock);
        assertEquals(true, isUsuarioValido);
    }

    @Test
    public void testValidateFailed() {

        final UsuarioForm userMock = new UsuarioForm("lol", "123", "gobierno");
        final Boolean isUsuarioValido = new LoginInteractor().validarUsuario(userMock);
        assertEquals(false, isUsuarioValido);
    }
}
