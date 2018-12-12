package ar.edu.ubp.das.src.consumers.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSConsumerDao extends DaoImpl<ConsumerForm> {

    public MSConsumerDao() {
        super(ConsumerForm.class);
    }

    @Override
    public void insert(final ConsumerForm bean) throws SQLException {
        this.executeProcedure("dbo.insert_consumers(?, ?, ?, ?, ?)", bean,
                "documento", "nombre", "apellido", "nroTelefono", "email");
    }

    @Override
    public void update(final ConsumerForm bean) throws SQLException {
        this.executeProcedure("dbo.update_consumers(?, ?, ?)", bean,
                "documento", "nroTelefono", "email");
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
        return this.executeQueryProcedure("dbo.get_consumer_by_documento(?)",
                form, "documento")
                .stream()
                .findFirst()
                .isPresent();
    }

    public Optional<ConsumerForm> selectConsumerByDni(ConsumerForm form) throws SQLException {

        return this.executeQueryProcedure("dbo.get_consumer_by_documento(?)",
                form, "documento")
                .stream()
                .findFirst();

    }


    public Optional<ConsumerForm> selectConsumerByDniAndConcesionaria(Long documento, Long concesionaria) throws SQLException {

        this.connect();
        this.setProcedure("dbo.get_consumer_by_documento_and_concesionaria(?, ?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, documento);
        this.setParameter(2, concesionaria);
        final Optional<ConsumerForm> result = this.executeQuery().stream().findFirst();
        this.disconnect();
        return result;

    }

    public void addUsername(final ConsumerForm bean) throws SQLException {
        this.executeProcedure("dbo.add_username_consumers(?, ?)", bean, "documento", "username");
    }
}
