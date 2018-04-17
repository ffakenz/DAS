package interactors;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.login.daos.MSLogInDao;
import ar.edu.ubp.das.src.login.forms.LogInForm;
import ar.edu.ubp.das.src.login.interactors.LogInImpl;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LogInImplTest {

    private static List<DynaActionForm> db = new ArrayList<>();

    private static class MSLogInDaoMock extends MSLogInDao {

        @Override
        public void insert(DynaActionForm form) throws SQLException {
            ((LogInForm) form).setLoginTime(Date.valueOf("2018-04-17"));
            db.add(form);
        }

            @Override
        public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
            return db;
        }
    }


    @Test
    public void testLoginSuccessfully() {
        MSLogInDaoMock daoMock = new MSLogInDaoMock();

        LogInForm loginFormMock = new LogInForm();
        loginFormMock.setTipo("gobierno");
        loginFormMock.setUsername("pepe");

        assertEquals(false, db.contains(loginFormMock));

        LogInImpl logInImpl = new LogInImpl();
        logInImpl.login(loginFormMock).accept(daoMock);

        assertEquals(true, db.contains(loginFormMock));
    }
}
