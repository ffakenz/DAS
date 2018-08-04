package ar.edu.ubp.das.src.clientes.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.clientes.forms.ClienteForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSClientesDao extends DaoImpl<ClienteForm> {

    @Override
    public ClienteForm make(final ResultSet result) throws SQLException {
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
    public void insert(final ClienteForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_cliente(?, ?, ?, ?, ?, ?)");
        this.setParameter(1, form.getDocumento());
        this.setParameter(2, form.getNombre());
        this.setParameter(3, form.getApellido());
        this.setParameter(4, form.getNroTelefono());
        this.setParameter(5, form.getEmail());
        this.setParameter(6, form.getConcesionariaId());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final ClienteForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.update_cliente(?, ?, ?)");
        this.setParameter(1, form.getId());
        this.setParameter(2, form.getEmail());
        this.setParameter(3, form.getNroTelefono());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final ClienteForm form) throws SQLException {

    }

    @Override
    public List<ClienteForm> select(final ClienteForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_clientes", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<ClienteForm> clientes = this.executeQuery();
        this.disconnect();
        return clientes;
    }

    @Override
    public boolean valid(final ClienteForm form) throws SQLException {
        return false;
    }
}
