package interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;
import login.LoginInteractor;
import mocks.MSLogInDaoMock;
import mocks.MSUsuariosDaoMock;
import org.junit.Test;
import java.util.*;
import java.util.function.BiFunction;

public class LoginInteractorTest {

    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if(daoName.equals("Usuarios") && daoPackage.equals("login")) {
            Dao dao = new MSUsuariosDaoMock();
            return dao;
        }
        else if (daoName.equals("LogIn") && daoPackage.equals("login")) {
            Dao dao = new MSLogInDaoMock();
            return dao;
        }
        else return null;
    };


    @Test
    public void testLoginInteractor() {
        Interactor action = new LoginInteractor();

        DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("username", "pepe");
        userForm.setItem("password", "123");

        InteractorResponse response = action.execute(userForm).apply(daoFactoryMock);

        assert(response.getResponse() == ResponseForward.SUCCESS);
        assert(((Optional<Long>)response.getResult()).orElse(Long.MIN_VALUE) == 1);

    }
}
