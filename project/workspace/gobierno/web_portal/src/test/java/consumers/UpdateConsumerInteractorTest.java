package consumers;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.UpdateConsumerInteractor;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateConsumerInteractorTest {

    MSConsumerDao msConsumerDao;
    UpdateConsumerInteractor updateConsumerInteractor;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(dataSourceConfig);
        updateConsumerInteractor = new UpdateConsumerInteractor(msConsumerDao);
    }

    @Test
    public void test10_Update_consumer_missing_input_value() throws SQLException {
        DynaActionForm consumerForm = new DynaActionForm();
        consumerForm.setItem("documento", "777");
        // consumerForm.setItem("concesionaria", "1");
        consumerForm.setItem("nro_telefono", "Tel_Test_01");
        consumerForm.setItem("email", "test@test.com");

        // Update the consumer
        InteractorResponse<Boolean> result = updateConsumerInteractor.execute(consumerForm);
        assertEquals(ResponseForward.WARNING, result.getResponse());
        assertFalse(result.getResult());
    }

    @Test
    public void test11_Update_email_consumer_successfully() throws SQLException {
        DynaActionForm consumerForm = new DynaActionForm();
        consumerForm.setItem("documento", "777");
        consumerForm.setItem("concesionaria", "1");
        consumerForm.setItem("email", "test@test.com");

        // Insert the consumer
        InteractorResponse<Boolean> result = updateConsumerInteractor.execute(consumerForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());
    }

    @Test
    public void test12_Update_telefono_consumer_successfully() throws SQLException {
        DynaActionForm consumerForm = new DynaActionForm();
        consumerForm.setItem("documento", "777");
        consumerForm.setItem("concesionaria", "1");
        consumerForm.setItem("nro_telefono", "Tel_Test_01");

        // Insert the consumer
        InteractorResponse<Boolean> result = updateConsumerInteractor.execute(consumerForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());
    }

    @Test
    public void test13_Update_both_values_consumer_successfully() throws SQLException {
        DynaActionForm consumerForm = new DynaActionForm();
        consumerForm.setItem("documento", "777");
        consumerForm.setItem("concesionaria", "1");
        consumerForm.setItem("nro_telefono", "Tel_Test_01");
        consumerForm.setItem("email", "test@test.com");

        // Insert the consumer
        InteractorResponse<Boolean> result = updateConsumerInteractor.execute(consumerForm);
        assertEquals(ResponseForward.SUCCESS, result.getResponse());
        assertTrue(result.getResult());
    }
}