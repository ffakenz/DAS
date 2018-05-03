package boundaries.clientes;

import clientes.RegistrarClienteInteractor;
import clientes.boundaries.RegistrarCliente;
import clientes.forms.ClienteForm;
import mocks.MSClienteDaoMock;
import mocks.MSConcesionariasDaoMock;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.util.Optional;

public class RegistrarClienteTest {

    public MSClienteDaoMock msClienteDaoMock;

    @Test
    public void validarRegistroClienteOK() {
        msClienteDaoMock = new MSClienteDaoMock();

        RegistrarCliente registrador = new RegistrarClienteInteractor();

        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(37575567L);
        clienteForm.setNombre("Diego");
        clienteForm.setApellido("Maradona");
        clienteForm.setNro_telefono("351-121233423");
        clienteForm.setEmail("diegote@mail.com");
        clienteForm.setConcesionaria(1L);

        Optional<Long> response = registrador.registrarCliente(clienteForm).apply(msClienteDaoMock);

        assertEquals(new Long(4), response.get());
    }

    @Test
    public void validarRegistroClienteThatExist() {
        msClienteDaoMock = new MSClienteDaoMock();

        RegistrarCliente registrador = new RegistrarClienteInteractor();

        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(1L);
        clienteForm.setConcesionaria(1L);

        Optional<Long> response = registrador.registrarCliente(clienteForm).apply(msClienteDaoMock);

        assertEquals(Optional.empty(), response);
    }


}
