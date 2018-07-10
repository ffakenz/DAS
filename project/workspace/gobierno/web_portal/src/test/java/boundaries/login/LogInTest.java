package boundaries.login;

import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import mocks.MSLogInDaoMock;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LogInTest {

    @Test
    public void testMockDBIsEmpty() {
        final MSLogInDaoMock loginDao = new MSLogInDaoMock();
        final LogInForm loginRqst = new LogInForm();
        loginRqst.setUsername("pepe");

        assertEquals(false, loginDao.logins.contains(loginRqst));
    }

    @Test
    public void testLoginSuccessfully() {
        final MSLogInDaoMock loginDao = new MSLogInDaoMock();
        final LogInForm loginRqst = new LogInForm();
        loginRqst.setUsername("pepe");

        final LogIn logueador = new LoginInteractor();
        final Optional<Long> logInId = logueador.login(loginRqst).apply(loginDao);

        assertEquals(true, loginDao.logins.contains(loginRqst));
        assertEquals(logInId, Optional.of(new Long(1)));
        assert (logueador.isLoggedIn(loginRqst).apply(loginDao).equals(logInId));
    }

}
