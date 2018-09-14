package ar.edu.ubp.das.src.consumers.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.core.Manager;

import java.sql.SQLException;
import java.util.Optional;

public class ConsumerManager extends Manager<MSConsumerDao> {

    public ConsumerManager(final DaoImpl dao) {
        super(dao);
    }
}
