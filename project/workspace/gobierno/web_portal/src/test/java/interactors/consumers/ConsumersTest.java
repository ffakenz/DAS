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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumersTest {

    MSConsumerDao msConsumerDao;
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

    @Test
    public void test_12_Update_consumer_personal_data_successfully() throws SQLException {

        ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(777L);
        consumerForm.setConcesionaria(1L);

        //actualizo telefono y seteo email en null
        consumerForm.setNroTelefono("Tel_Test_Actualizado");
        consumerManager.update(consumerForm);

        //valido que el telefono este actualizado y el email sea el que tenia previamente
        Optional<ConsumerForm> consumer = consumerManager.selectConsumerByDniAndConcesionaria(consumerForm);
        assertEquals("Tel_Test_Actualizado", consumer.get().getNroTelefono());
        assertEquals(null, consumer.get().getEmail());

        //actualizo el email
        consumerForm.setEmail("test@actualizado.com");
        consumerForm.setNroTelefono(null);
        consumerManager.update(consumerForm);

        //valido que el email este actualizado y el telefono sea el que tenia luego de la primer actualizacion
        consumer = consumerManager.selectConsumerByDniAndConcesionaria(consumerForm);
        assertEquals(null, consumer.get().getNroTelefono());
        assertEquals("test@actualizado.com", consumer.get().getEmail());

        //actualizo el email y el telefono
        consumerForm.setNroTelefono("Tel_Actualizado_2");
        consumerForm.setEmail("test@actualizado2.com");
        consumerManager.update(consumerForm);

        //valido que el telefono y el email este actualizado
        consumer = consumerManager.selectConsumerByDniAndConcesionaria(consumerForm);
        assertEquals("Tel_Actualizado_2", consumer.get().getNroTelefono());
        assertEquals("test@actualizado2.com", consumer.get().getEmail());
    }

}
