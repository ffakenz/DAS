package interactors.consumers;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.ConsumerInteractor;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.consumer.ConsumerManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumersTest {

    MSConsumerDao msConsumerDao;
    ConsumerInteractor consumerInteractor;
    ConsumerManager consumerManager;

    @Before
    public void setup() {
        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(dataSourceConfig);

        consumerManager = new ConsumerManager(msConsumerDao);

        consumerInteractor = new ConsumerInteractor(consumerManager);
    }

    @Test
    public void test_10_Validate_consumer_doesnt_exist() throws SQLException {

        ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(777L);
        consumerForm.setConcesionaria(1L);

        Optional<ConsumerForm> consumers = consumerManager.selectConsumerByDniAndConcesionaria(consumerForm);

        assertFalse(consumers.isPresent());
    }

    @Test
    public void test_11_Create_consumer_successfully() throws SQLException {

        ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(777L);
        consumerForm.setConcesionaria(1L);
        consumerForm.setNombre("Nombre_Test_1");
        consumerForm.setApellido("Apellido_Test_1");
        consumerForm.setNroTelefono("Tel_Test_01");
        consumerForm.setEmail("test@test.com");

        consumerManager.insert(consumerForm);

        Optional<ConsumerForm> consumer = consumerManager.selectConsumerByDniAndConcesionaria(consumerForm);

        assertTrue(consumer.isPresent());
    }

}
