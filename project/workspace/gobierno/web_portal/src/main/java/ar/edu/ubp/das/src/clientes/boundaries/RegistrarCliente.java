package ar.edu.ubp.das.src.clientes.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;

import java.util.Optional;
import java.util.function.Function;

public interface RegistrarCliente {
    Function<Dao, Optional<Long>> registrarCliente(ClienteForm form);
}
