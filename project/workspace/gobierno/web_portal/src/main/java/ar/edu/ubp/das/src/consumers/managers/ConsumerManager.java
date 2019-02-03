package ar.edu.ubp.das.src.consumers.managers;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.core.Manager;

public class ConsumerManager extends Manager<MSConsumerDao> {


    public ConsumerManager(final DaoImpl dao) {
        super(dao);
    }
}
