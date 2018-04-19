package interactors;

import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.interactors.LoginInteractor;
import mocks.MSLogInDaoMock;
import org.junit.Test;
import java.util.Optional;


import static org.junit.Assert.assertEquals;

public class LogInImplTest {

    @Test
    public void testMockDBIsEmpty() {
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        assertEquals(false, msLogInDaoMock.db.contains(loginFormMock));
    }

    @Test
    public void testLoginSuccessfully() {
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        LoginInteractor logInImpl = new LoginInteractor();
        Optional<Long> logInId = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(true, msLogInDaoMock.db.contains(loginFormMock));
        assertEquals(logInId, Optional.of(new Long(1)));
    }

    @Test
    public void loginTwice(){
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        LoginInteractor logInImpl = new LoginInteractor();
        Optional<Long> logInId = logInImpl.login(loginFormMock).apply(msLogInDaoMock);
        Optional<Long> logInId2 = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(logInId2, Optional.of(new Long(2)));
    }

    @Test
    public void loginOther(){
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("other");

        LoginInteractor logInImpl = new LoginInteractor();
        Optional<Long> logInId2 = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(logInId2, Optional.of(new Long(1)));
    }
}
