package ar.edu.ubp.das.src.usuarios;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioUpdateInteractorTest {

    MSUsuariosDao msUsuariosDao;
    UsuarioUpdateInteractor interactor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

        interactor = new UsuarioUpdateInteractor(msUsuariosDao);
    }

    @Test
    public void test10_Verify_create_user_ok() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("documento", "222");
        userForm.setItem("username", "newuser");
        userForm.setItem("password", "newpass");

        final InteractorResponse<Boolean> response = interactor.execute(userForm);

        assertEquals(ResponseForward.SUCCESS, response.getResponse());
    }

//    @Test(expected = SQLException.class)
//    public void test11_Verify_create_user_twice_fail() throws SQLException {
//
//        final DynaActionForm userForm = new DynaActionForm();
//        userForm.setItem("documento", "111");
//        userForm.setItem("username", "pepe2");
//        userForm.setItem("password", "asd");
//
//        interactor.execute(userForm);
//    }

    @Test
    public void test12_Verify_create_user_fail_parameters() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("documento", "111");
        userForm.setItem("username", "pepe2");

        final InteractorResponse<Boolean> response = interactor.execute(userForm);

        assertEquals(ResponseForward.WARNING, response.getResponse());
    }

    @Test
    public void test13_Verify_create_user_fails_unexistent_consumer() throws SQLException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("documento", "1231231231");
        userForm.setItem("username", "pepe2");
        userForm.setItem("password", "asd");

        final InteractorResponse<Boolean> response = interactor.execute(userForm);

        assertEquals(ResponseForward.FAILURE, response.getResponse());
    }


    // TODO: review handle exceptions in interactor
    @Test(expected = NumberFormatException.class)
    public void test14_Verify_create_user_invalid_document_format() throws SQLException, NumberFormatException {

        final DynaActionForm userForm = new DynaActionForm();
        userForm.setItem("documento", "sadadasda");
        userForm.setItem("username", "pepe3");
        userForm.setItem("password", "asd");

        interactor.execute(userForm);
    }


}
