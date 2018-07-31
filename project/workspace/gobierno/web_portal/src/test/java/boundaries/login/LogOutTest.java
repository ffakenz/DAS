package boundaries.login;

import ar.edu.ubp.das.src.login.LoginInteractor;
import ar.edu.ubp.das.src.login.boundaries.LogIn;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Optional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogOutTest {

    MSLogInDao loginDao = new MSLogInDao();

    @Test
    public void testLoginSuccessfully() {
        final LogIn logueador = new LoginInteractor();
        // create a login request
        final LogInForm logRqst = new LogInForm();
        logRqst.setUsername("pepe");
        // try to login the user
        final Optional<Long> logInId = logueador.login(logRqst).apply(loginDao);
        // verify the user is logged in
        assert (!logueador.isLoggedIn(logRqst).apply(loginDao).equals(Optional.empty()));
        // verify he has a login id
        assert (logInId.equals(Optional.of(new Long(1))));
        // log out using same request ?
        logueador.logout(logRqst).accept(loginDao);
        // verify the user is not logged in
        assert (logueador.isLoggedIn(logRqst).apply(loginDao).equals(Optional.empty()));
    }
}
