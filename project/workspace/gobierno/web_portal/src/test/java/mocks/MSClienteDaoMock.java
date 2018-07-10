package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSClienteDaoMock implements Dao {

    public List<ClienteForm> clientes;

    public MSClienteDaoMock() {
        clientes = new ArrayList<>();

        final ClienteForm c1 = new ClienteForm();
        c1.setId(1L);
        c1.setDocumento(1L);
        c1.setNombre("Lionel");
        c1.setApellido("Messi");
        c1.setNroTelefono("351-1243423");
        c1.setEmail("liopulgoso@mail.com");
        c1.setFechaAlta(Timestamp.from(Instant.now()));
        c1.setConcesionariaId(1L);

        final ClienteForm c2 = new ClienteForm();
        c2.setId(2L);
        c2.setDocumento(2L);
        c2.setNombre("Leonardo");
        c2.setApellido("Ponzio");
        c2.setNroTelefono("351-1243423");
        c2.setEmail("leon@mail.com");
        c2.setFechaAlta(Timestamp.from(Instant.now()));
        c2.setConcesionariaId(2L);

        final ClienteForm c3 = new ClienteForm();
        c3.setId(3L);
        c3.setDocumento(3L);
        c3.setNombre("Franco");
        c3.setApellido("Armani");
        c3.setNroTelefono("351-1243423");
        c3.setEmail("francoarmani@mail.com");
        c3.setFechaAlta(Timestamp.from(Instant.now()));
        c3.setConcesionariaId(3L);

        clientes.addAll(Arrays.asList(c1, c2, c3));
    }

    @Override
    public DynaActionForm make(final ResultSet result) {
        return null;
    }

    @Override
    public void insert(final DynaActionForm form) {
        final Optional<Long> max =
                clientes.stream()
                        .map(c -> c.getId())
                        .max(Long::compareTo);

        final ClienteForm clienteForm = (ClienteForm) form;

        clienteForm.setId(max.orElse(Long.valueOf(0)) + 1);
        clienteForm.setDocumento(clienteForm.getDocumento());
        clienteForm.setNombre(clienteForm.getNombre());
        clienteForm.setApellido(clienteForm.getApellido());
        clienteForm.setNroTelefono(clienteForm.getNroTelefono());
        clienteForm.setEmail(clienteForm.getEmail());
        clienteForm.setFechaAlta(Timestamp.from(Instant.now()));
        clienteForm.setConcesionariaId(clienteForm.getConcesionariaId());

        clientes.add(clienteForm);
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        final ClienteForm formCliente = (ClienteForm) form;

        clientes.stream().filter(c -> c.getId() == formCliente.getId())
                .findFirst().ifPresent(c -> {
            clientes.remove(c);
            clientes.add(formCliente);
        });
    }

    @Override
    public void delete(final DynaActionForm form) {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) {
        return clientes.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(final DynaActionForm form) {
        return false;
    }
}
