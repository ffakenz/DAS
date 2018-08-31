package ar.edu.ubp.das.src.consumers.model.consumer;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConsumerManager {

    MSConsumerDao msConsumerDao;

    public ConsumerManager(final DaoImpl msConsumerDao) {
        this.msConsumerDao = (MSConsumerDao) msConsumerDao;
    }

    public List<ConsumerForm> selectAll() throws SQLException {
        return msConsumerDao.select(null);
    }

    public Optional<ConsumerForm> selectConsumerByDniAndConcesionaria(final ConsumerForm form) throws SQLException {
        return msConsumerDao.selectConsumerByDniAndConcesionaria(form);
    }

    public void insert(final ConsumerForm consumerForm) throws SQLException {
        msConsumerDao.insert(consumerForm);
    }

    public void update(final ConsumerForm consumerForm) throws SQLException {
        msConsumerDao.update(consumerForm);
    }
}
