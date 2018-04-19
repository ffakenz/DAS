package actions;

import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.login.actions.ValidarUsuarioAction;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import mocks.HttpSessionMock;
import mocks.MSLogInDaoMock;
import mocks.MSUsuariosDaoMock;
import mocks.UtilsForwardsMock;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiFunction;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidarUsuarioActionTest {

    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if(daoName.equals("Usuarios") && daoPackage.equals("login")) {
            MSUsuariosDao dao = new MSUsuariosDaoMock();
            return dao;
        }
        else if (daoName.equals("LogIn") && daoPackage.equals("login")) {
            MSLogInDao dao = new MSLogInDaoMock();
            return dao;
        }
        else return null;
    };


    @Test
    public void testThis() throws SQLException {
        ValidarUsuarioAction action = new ValidarUsuarioAction();

        Map<String, ForwardConfig>   forwards = UtilsForwardsMock.forwardsMock();

        ActionMapping actionMappingMock = new ActionMapping();
        actionMappingMock.setForwards(forwards);

        DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "123");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSessionMock session = new HttpSessionMock();
        when(request.getSession()).thenReturn(session);

        ForwardConfig forward = action.execute(actionMappingMock, userForm, request, null).apply(daoFactoryMock);

        assert(forward.getName().equals("success"));

        assert(session.getAttribute("LogInId") == Long.valueOf(1));

    }
}
