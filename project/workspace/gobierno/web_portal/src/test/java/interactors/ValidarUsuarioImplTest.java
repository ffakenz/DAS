package interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.forms.UsuarioForm;
import ar.edu.ubp.das.src.login.interactors.ValidarUsuarioImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ValidarUsuarioImplTest {

    private static class MSUsuariosDaoMock extends MSUsuariosDao {
        @Override
        public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
            UsuarioForm userMock = new UsuarioForm();
            userMock.setTipo("gobierno");
            userMock.setUsername("pepe");
            userMock.setPassword("123");
            List<DynaActionForm> usuarios  = Arrays.asList(userMock);
            return usuarios;
        }
    }


    @Test
    public void testValidateSuccessfully() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        UsuarioForm userMock = new UsuarioForm();
        userMock.setTipo("gobierno");
        userMock.setUsername("pepe");
        userMock.setPassword("123");

        Boolean isUsuarioValido =  new ValidarUsuarioImpl().validarUsuario(userMock).apply(daoMock);
        assertEquals(true, isUsuarioValido);
    }

    @Test
    public void testValidateFailed() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        UsuarioForm userMock = new UsuarioForm();
        userMock.setTipo("gobierno");
        userMock.setUsername("lol");
        userMock.setPassword("123");

        Boolean isUsuarioValido =  new ValidarUsuarioImpl().validarUsuario(userMock).apply(daoMock);
        assertEquals(false, isUsuarioValido);
    }
}
