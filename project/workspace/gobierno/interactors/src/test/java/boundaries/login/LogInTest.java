package boundaries.login;

import login.LoginInteractor;
import login.boundaries.LogIn;
import login.forms.LogInForm;
import mocks.MSLogInDaoMock;
import org.junit.Test;
import java.util.Optional;


import static org.junit.Assert.assertEquals;

public class LogInTest {

    @Test
    public void testMockDBIsEmpty() {
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        assertEquals(false, msLogInDaoMock.logins.contains(loginFormMock));
    }

    @Test
    public void testLoginSuccessfully() {
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        LogIn logInImpl = new LoginInteractor();
        Optional<Long> logInId = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(true, msLogInDaoMock.logins.contains(loginFormMock));
        assertEquals(logInId, Optional.of(new Long(1)));
    }

    @Test
    public void loginTwice(){
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        LogIn logInImpl = new LoginInteractor();
        Optional<Long> logInId = logInImpl.login(loginFormMock).apply(msLogInDaoMock);
        Optional<Long> logInId2 = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(logInId2, Optional.empty());
    }

    @Test
    public void loginOther(){
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("other");

        LogIn logInImpl = new LoginInteractor();
        Optional<Long> logInId2 = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(logInId2, Optional.of(new Long(1)));
    }
}
