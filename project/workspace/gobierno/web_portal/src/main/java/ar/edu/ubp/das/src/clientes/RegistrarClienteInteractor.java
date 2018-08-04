package ar.edu.ubp.das.src.clientes;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.clientes.boundaries.RegistrarCliente;
import ar.edu.ubp.das.src.clientes.boundaries.Utils;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;
import ar.edu.ubp.das.src.core.UtilsCore;

import java.sql.SQLException;

public class RegistrarClienteInteractor implements RegistrarCliente, UtilsCore, Utils {

    private final Dao clienteDao = DaoFactory.getDao("Clientes", "clientes");


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
