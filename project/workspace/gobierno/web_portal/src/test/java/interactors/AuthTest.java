package interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.boundries.login.LogInReq;
import ar.edu.ubp.das.src.boundries.login.LogInResp;
import ar.edu.ubp.das.src.interactors.Auth;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.forms.UserForm;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AuthTest {

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
    public void testLoginDummy() {
        MSUsuariosDaoMock daoMock = new MSUsuariosDaoMock();
        LogInResp logInResp =
            new Auth(daoMock).logIn(
                    new LogInReq("pepe", "123")
            );

        String result = logInResp.getResult();

        assertEquals("c", result);


        LogInResp logInResp2 =
                new Auth(daoMock).logIn(
                        new LogInReq("lol", "123")
                );

        String result2 = logInResp2.getResult();

        assertEquals("e", result2);
    }
}
