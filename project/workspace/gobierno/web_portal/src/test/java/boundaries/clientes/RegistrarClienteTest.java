package boundaries.clientes;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.clientes.RegistrarClienteInteractor;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.daos.MSClientesDao;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrarClienteTest {

    MSClientesDao clienteDao;
    DatasourceConfig dataSourceConfig;

    @Before
    public void setup() {
        dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        clienteDao = new MSClientesDao();
        clienteDao.setDatasource(dataSourceConfig);
    }

    @Test
    public void test01validarRegistroClienteOK() throws SQLException {

        final RegistrarCliente registrador = new RegistrarClienteInteractor();

        final ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(37575567L);
        clienteForm.setNombre("Diego");
        clienteForm.setApellido("Maradona");
        clienteForm.setNroTelefono("351-121233423");
        clienteForm.setEmail("diegote@mail.com");
        clienteForm.setConcesionariaId(1L);

        registrador.registrarCliente(clienteForm);

        assert (clienteDao.select(null).stream().anyMatch(d -> {
            final ClienteForm c = (ClienteForm) d;
            return c.getConcesionariaId() == clienteForm.getConcesionariaId() &&
                    c.getDocumento() == clienteForm.getDocumento();
        }));

    }

    @Test
    public void validarRegistroClienteThatExist() throws SQLException {

        final RegistrarCliente registrador = new RegistrarClienteInteractor();

        final ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(1L);
        clienteForm.setConcesionariaId(1L);

        registrador.registrarCliente(clienteForm);

        assert (clienteDao.select(null).stream().anyMatch(d -> {
            final ClienteForm c = (ClienteForm) d;
            return c.getConcesionariaId() == clienteForm.getConcesionariaId() &&
                    c.getDocumento() == clienteForm.getDocumento();
        }));
    }

}
