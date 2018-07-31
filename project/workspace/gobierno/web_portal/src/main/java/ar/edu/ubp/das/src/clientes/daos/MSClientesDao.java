package ar.edu.ubp.das.src.clientes.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSClientesDao extends DaoImpl {

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        final ClienteForm clienteForm = new ClienteForm();

        clienteForm.setId(result.getLong("id"));
        clienteForm.setDocumento(result.getLong("documento"));
        clienteForm.setNombre(result.getString("nombre"));
        clienteForm.setApellido(result.getString("apellido"));
        clienteForm.setNroTelefono(result.getString("nro_telefono"));
        clienteForm.setEmail(result.getString("email"));
        clienteForm.setFechaAlta(result.getTimestamp("fecha_de_alta"));
        clienteForm.setConcesionariaId(result.getLong("concesionaria"));

        return clienteForm;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_cliente(?, ?, ?, ?, ?, ?)");
        final ClienteForm f = (ClienteForm) form;
        this.setParameter(1, f.getDocumento());
        this.setParameter(2, f.getNombre());
        this.setParameter(3, f.getApellido());
        this.setParameter(4, f.getNroTelefono());
        this.setParameter(5, f.getEmail());
        this.setParameter(6, f.getConcesionariaId());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.update_cliente(?, ?, ?)");
        final ClienteForm f = (ClienteForm) form;
        this.setParameter(1, f.getId());
        this.setParameter(2, f.getEmail());
        this.setParameter(3, f.getNroTelefono());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_clientes", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<DynaActionForm> clientes = this.executeQuery();
        this.disconnect();
        return clientes;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}