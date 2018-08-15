package ar.edu.ubp.das.src.consumers.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConsumerDao extends DaoImpl<ConsumerForm> {

    public MSConsumerDao() {
        super(ConsumerForm.class);
    }

    @Override
    public void insert(final ConsumerForm bean) throws SQLException {
        this.executeProcedure("dbo.insert_consumers(?, ?, ?, ?, ?, ?)", bean,
                "documento", "nombre", "apellido", "nroTelefono",
                "email", "concesionaria");
    }

    @Override
    public void update(final ConsumerForm bean) throws SQLException {
        this.executeProcedure("dbo.update_consumers(?, ?, ?, ?)", bean,
                "documento", "concesionaria", "nroTelefono", "email");
    }

    @Override
    public void delete(final ConsumerForm form) throws SQLException {

    }

    @Override
    public List<ConsumerForm> select(final ConsumerForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_consumers", form);
    }

    @Override
    public boolean valid(final ConsumerForm form) throws SQLException {
        return false;
    }
}
