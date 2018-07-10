package boundaries.clientes;



import ar.edu.ubp.das.src.clientes.RegistrarClienteInteractor;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;
import mocks.MSClienteDaoMock;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarClienteTest {

    public MSClienteDaoMock msClienteDaoMock;

    @Test
    public void validarRegistroClienteOK() {
        msClienteDaoMock = new MSClienteDaoMock();

        final RegistrarCliente registrador = new RegistrarClienteInteractor();

        final ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(37575567L);
        clienteForm.setNombre("Diego");
        clienteForm.setApellido("Maradona");
        clienteForm.setNroTelefono("351-121233423");
        clienteForm.setEmail("diegote@mail.com");
        clienteForm.setConcesionariaId(1L);

        final Optional<Long> response = registrador.registrarCliente(clienteForm).apply(msClienteDaoMock);

        assertEquals(new Long(4), response.get());
    }

    @Test
    public void validarRegistroClienteThatExist() {
        msClienteDaoMock = new MSClienteDaoMock();

        final RegistrarCliente registrador = new RegistrarClienteInteractor();

        final ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(1L);
        clienteForm.setConcesionariaId(1L);

        final Optional<Long> response = registrador.registrarCliente(clienteForm).apply(msClienteDaoMock);

        assertEquals(Optional.empty(), response);
    }

}
