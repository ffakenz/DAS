package ar.edu.ubp.das.src.clientes;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.boundaries.Utils;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;

import java.sql.SQLException;

public class RegistrarClienteInteractor implements RegistrarCliente, Utils {

    private Dao clienteDao;

    public RegistrarClienteInteractor(final Dao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public void registrarCliente(final ClienteForm form) {
        if (!testClienteRegistrado(form).apply(clienteDao)) {
            try {
                clienteDao.insert(form);
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
