package boundaries.clientes;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.src.clientes.RegistrarClienteInteractor;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.daos.MSClientesDao;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.SQLException;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ModuleConfigImpl.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrarClienteTest {

    MSClientesDao clienteDao;
    DatasourceConfig dataSourceConfig;
    ModuleConfigImpl moduleConfigImpl;

    @Before
    public void setup() {
        moduleConfigImpl = mock(ModuleConfigImpl.class);

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
        mockStatic(ModuleConfigImpl.class);
        Mockito.when(moduleConfigImpl);

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
