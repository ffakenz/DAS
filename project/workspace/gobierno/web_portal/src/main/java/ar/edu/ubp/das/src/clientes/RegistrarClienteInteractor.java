package ar.edu.ubp.das.src.clientes;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.boundaries.Utils;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;
import ar.edu.ubp.das.src.core.UtilsCore;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public class RegistrarClienteInteractor implements RegistrarCliente, UtilsCore, Utils {

    @Override
    public Function<Dao, Optional<Long>> registrarCliente(final ClienteForm form) {
        return clienteDao -> {
            if (!testClienteRegistrado(form).apply(clienteDao)) {
                try {
                    clienteDao.insert(form);
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
                return getIdOf(c -> {
                    ClienteForm clienteForm = (ClienteForm) c;
                    clienteForm.setItem("id", clienteForm.getId().toString());
                    return
                            clienteForm.getDocumento() == form.getDocumento() &&
                                    clienteForm.getConcesionariaId() == form.getConcesionariaId();
                }).apply(clienteDao);
            } else {
                return Optional.empty();
            }
        };
    }

}
