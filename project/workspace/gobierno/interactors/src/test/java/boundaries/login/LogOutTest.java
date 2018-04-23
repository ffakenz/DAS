package boundaries.login;

import login.LoginInteractor;
import login.boundaries.LogIn;
import login.forms.LogInForm;
import mocks.MSLogInDaoMock;
import org.junit.Test;

import java.util.Optional;


public class LogOutTest {

    @Test
    public void testLoginSuccessfully() {
        MSLogInDaoMock loginDao = new MSLogInDaoMock();
        LogIn logueador = new LoginInteractor();
        // create a login request
        LogInForm logRqst = new LogInForm();
        logRqst.setUsername("pepe");
        // try to login the user
        Optional<Long> logInId = logueador.login(logRqst).apply(loginDao);
        // verify the user is logged in
        assert(!logueador.isLoggedIn(logRqst).apply(loginDao).equals(Optional.empty()));
        // verify he has a login id
        assert(logInId.equals(Optional.of(new Long(1))));
        // log out using same request ?
        logueador.logout(logRqst).accept(loginDao);
        // verify the user is not logged in
        assert(logueador.isLoggedIn(logRqst).apply(loginDao).equals(Optional.empty()));
    }
}
