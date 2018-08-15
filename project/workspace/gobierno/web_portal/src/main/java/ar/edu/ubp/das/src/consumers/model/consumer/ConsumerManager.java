package ar.edu.ubp.das.src.consumers.model.consumer;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConsumerManager {

    MSConsumerDaoEx msConsumerDaoEx;

    public ConsumerManager(final DaoImpl msConsumerDaoEx) {
        this.msConsumerDaoEx = new MSConsumerDaoEx(msConsumerDaoEx);
    }

    public List<ConsumerForm> selectAll() throws SQLException{
        return msConsumerDaoEx.select( null);
    }

    public Optional<ConsumerForm> selectConsumerByDniAndConcesionaria(ConsumerForm form) throws SQLException {
        return msConsumerDaoEx.selectConsumerByDniAndConcesionaria(form);
    }

    public void insert(ConsumerForm consumerForm) throws SQLException {
        msConsumerDaoEx.insert(consumerForm);
    }
}
