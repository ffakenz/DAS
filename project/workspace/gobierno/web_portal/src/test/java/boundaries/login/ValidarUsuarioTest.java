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

        final UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("pepe");
        userMock.setPassword("123");
        userMock.setRol("gobierno");

        final Boolean isUsuarioValido = new LoginInteractor().validarUsuario(userMock).apply(dao);
        assertEquals(true, isUsuarioValido);
    }

    @Test
    public void testValidateFailed() {

        final UsuarioForm userMock = new UsuarioForm();
        userMock.setUsername("lol");
        userMock.setPassword("123");
        userMock.setRol("gobierno");

        final Boolean isUsuarioValido = new LoginInteractor().validarUsuario(userMock).apply(dao);
        assertEquals(false, isUsuarioValido);
    }
}
