package interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.interactors.LogInImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;

public class LogInImplTest {



    private static class MSLogInDaoMock extends MSLogInDao {
        ArrayList<DynaActionForm> db = new ArrayList<>();

        @Override
        public void insert(DynaActionForm form) throws SQLException {
            Optional<Long> max =
                    db.stream()
                        .map(l -> ((LogInForm)l))
                        .filter( l -> l.getUsername().equals(((LogInForm) form).getUsername()) )
                        .map( l -> l.getId())
                        .max(Comparable::compareTo);

            ((LogInForm) form).setId(max.orElse(Long.valueOf(0)) + 1);
            db.add(form);
        }

            @Override
        public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
            return db;
        }
    }

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

        LogInImpl logInImpl = new LogInImpl();
        Optional<Long> logInId = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(true, msLogInDaoMock.db.contains(loginFormMock));
        assertEquals(logInId, Optional.of(new Long(1)));
    }

    @Test
    public void loginTwice(){
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("pepe");

        LogInImpl logInImpl = new LogInImpl();
        Optional<Long> logInId = logInImpl.login(loginFormMock).apply(msLogInDaoMock);
        Optional<Long> logInId2 = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(logInId2, Optional.of(new Long(2)));
    }

    @Test
    public void loginOther(){
        MSLogInDaoMock msLogInDaoMock = new MSLogInDaoMock();
        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setUsername("other");

        LogInImpl logInImpl = new LogInImpl();
        Optional<Long> logInId2 = logInImpl.login(loginFormMock).apply(msLogInDaoMock);

        assertEquals(logInId2, Optional.of(new Long(1)));
    }
}
