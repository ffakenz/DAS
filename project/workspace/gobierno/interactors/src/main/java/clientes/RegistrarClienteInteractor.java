package clientes;

import ar.edu.ubp.das.mvc.db.Dao;
import clientes.boundaries.RegistrarCliente;
import clientes.forms.ClienteForm;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public class RegistrarClienteInteractor implements RegistrarCliente {

    @Override
    public Function<Dao, Optional<Long>> registrarCliente(ClienteForm form) {
        return clienteDao -> {
            try {

                clienteDao.insert(form);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        };
    }
}
