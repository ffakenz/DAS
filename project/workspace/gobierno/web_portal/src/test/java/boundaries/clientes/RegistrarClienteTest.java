package boundaries.clientes;

import ar.edu.ubp.das.src.clientes.RegistrarClienteInteractor;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.daos.MSClientesDao;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrarClienteTest {

    MSClientesDao msClientesDao = new MSClientesDao();

    @Test
    public void test01validarRegistroClienteOK() {

        final RegistrarCliente registrador = new RegistrarClienteInteractor();

        final ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(37575567L);
        clienteForm.setNombre("Diego");
        clienteForm.setApellido("Maradona");
        clienteForm.setNroTelefono("351-121233423");
        clienteForm.setEmail("diegote@mail.com");
        clienteForm.setConcesionariaId(1L);

        final Optional<Long> response = registrador.registrarCliente(clienteForm).apply(msClientesDao);

        assertEquals(new Long(4), response.get());
    }

    @Test
    public void validarRegistroClienteThatExist() {

        final RegistrarCliente registrador = new RegistrarClienteInteractor();

        final ClienteForm clienteForm = new ClienteForm();
        clienteForm.setDocumento(1L);
        clienteForm.setConcesionariaId(1L);

        final Optional<Long> response = registrador.registrarCliente(clienteForm).apply(msClientesDao);

        assertEquals(Optional.empty(), response);
    }

}
