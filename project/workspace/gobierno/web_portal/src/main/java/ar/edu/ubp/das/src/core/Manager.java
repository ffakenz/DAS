package ar.edu.ubp.das.src.core;

import ar.edu.ubp.das.mvc.db.DaoImpl;

public class Manager<A> {
    protected A dao;

    public Manager(final DaoImpl dao) {
        this.dao = (A) dao;
    }

    public A getDao() {
        return this.dao;
    }
}
