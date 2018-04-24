package mocks;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import clientes.forms.ClienteForm;
import concesionarias.forms.ConcesionariaForm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSClienteDaoMock implements Dao {

    public List<ClienteForm> clientes;

    public MSClienteDaoMock() {
         clientes = new ArrayList<>();

        ClienteForm c1 = new ClienteForm();
        c1.setId(1L);
        c1.setDocumento(1L);
        c1.setNombre("Lionel");
        c1.setApellido("Messi");
        c1.setNro_telefono("351-1243423");
        c1.setEmail("liopulgoso@mail.com");
        c1.setFecha_de_alta(new Date(System.currentTimeMillis()));
        c1.setConcesionaria(1L);

        ClienteForm c2 = new ClienteForm();
        c2.setId(1L);
        c2.setDocumento(2L);
        c2.setNombre("Leonardo");
        c2.setApellido("Ponzio");
        c2.setNro_telefono("351-1243423");
        c2.setEmail("leon@mail.com");
        c2.setFecha_de_alta(new Date(System.currentTimeMillis()));
        c2.setConcesionaria(2L);

        ClienteForm c3 = new ClienteForm();
        c3.setId(1L);
        c3.setDocumento(3L);
        c3.setNombre("Franco");
        c3.setApellido("Armani");
        c3.setNro_telefono("351-1243423");
        c3.setEmail("francoarmani@mail.com");
        c3.setFecha_de_alta(new Date(System.currentTimeMillis()));
        c3.setConcesionaria(3L);

        clientes.addAll(Arrays.asList(c1,c2,c3));
    }

    @Override
    public DynaActionForm make(ResultSet result) {
        return null;
    }

    @Override
    public void insert(DynaActionForm form) {
        Optional<Long> max =
                clientes.stream()
                        .map( c -> c.getId())
                        .max(Long::compareTo);

        ClienteForm clienteForm = (ClienteForm)form;

        clienteForm.setId(max.orElse(Long.valueOf(0)) + 1);
        clienteForm.setDocumento(clienteForm.getDocumento());
        clienteForm.setNombre(clienteForm.getNombre());
        clienteForm.setApellido(clienteForm.getApellido());
        clienteForm.setNro_telefono(clienteForm.getNro_telefono());
        clienteForm.setEmail(clienteForm.getEmail());
        clienteForm.setFecha_de_alta(new Date(System.currentTimeMillis()));
        clienteForm.setConcesionaria(clienteForm.getConcesionaria());

        clientes.add(clienteForm);
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {
        ClienteForm formCliente = (ClienteForm) form;

        clientes.stream().filter( c -> c.getId() == formCliente.getId())
                .findFirst().ifPresent( c -> {
                    clientes.remove(c);
                    clientes.add(formCliente);
                });
    }

    @Override
    public void delete(DynaActionForm form) {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) {
        return clientes.stream().collect(Collectors.toList());
    }

    @Override
    public boolean valid(DynaActionForm form) {
        return false;
    }
}
