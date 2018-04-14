package interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.boundaries.LogInReq;
import ar.edu.ubp.das.src.login.boundaries.LogInResp;
import ar.edu.ubp.das.src.login.interactors.LogInImpl;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UserForm;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LogInImplTest {

    class MSUsuariosDaoMock extends MSUsuariosDao {
        @Override
        public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
            // TODO Auto-generated method stub
            UserForm userMock = new UserForm();
            userMock.setNombre("pepe");
            userMock.setPassword("123");

            List<DynaActionForm> usuarios  = Arrays.asList(userMock);

            return usuarios;
        }
    }


    @Test
    public void testLoginSuccessfully() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();
        LogInResp logInResp =
            new LogInImpl(daoMock).logIn(
                    new LogInReq("pepe", "123")
            );

        String result = logInResp.getResult();

        assertEquals("c", result);
    }

    @Test
    public void testLoginDenied() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();

        LogInResp logInResp =
                new LogInImpl(daoMock).logIn(
                        new LogInReq("lol", "123")
                );

        String result = logInResp.getResult();

        assertEquals("e", result);
    }
}
