package clientes.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import clientes.forms.ClienteForm;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;

import java.util.Optional;
import java.util.function.Function;

public interface RegistrarCliente {
    Function<Dao, Optional<Long>> registrarCliente(ClienteForm form);
}
