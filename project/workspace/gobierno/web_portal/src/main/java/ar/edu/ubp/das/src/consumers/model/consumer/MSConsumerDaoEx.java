package ar.edu.ubp.das.src.consumers.model.consumer;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.core.DaoExtender;

import java.sql.SQLException;
import java.util.Optional;

public class MSConsumerDaoEx extends DaoExtender<ConsumerForm> {

    public MSConsumerDaoEx(final DaoImpl dao) {
        super(dao, ConsumerForm.class);
    }

    public Optional<ConsumerForm> selectConsumerByDniAndConcesionaria(final ConsumerForm form) throws SQLException {
        return dao.executeQueryProcedure("dbo.get_consumer_by_documento_and_concesionaria(?, ?)",
                form, "documento", "concesionaria")
                .stream()
                .findFirst();
    }

}
