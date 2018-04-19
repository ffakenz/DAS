package actions;

import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.InteractorResponse;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.login.interactors.LoginInteractor;
import mocks.MSLogInDaoMock;
import mocks.MSUsuariosDaoMock;
import mocks.UtilsForwardsMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;
import java.util.function.BiFunction;

import static org.mockito.Mockito.mock;

public class LoginInteractorTest {

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
    public void testLoginInteractor() {
        LoginInteractor action = new LoginInteractor();

        Map<String, ForwardConfig>   forwards = UtilsForwardsMock.forwardsMock();

        ActionMapping actionMappingMock = new ActionMapping();
        actionMappingMock.setForwards(forwards);

        DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "123");

        InteractorResponse response = action.execute(actionMappingMock, userForm).apply(daoFactoryMock);

        assert(response.getForwardConfig().getName().equals("success"));
        assert(((Optional<Long>)response.getResult()).orElse(Long.MIN_VALUE) == 1);

    }
}
