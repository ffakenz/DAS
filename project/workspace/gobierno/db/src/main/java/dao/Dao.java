package dao;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Dao {

    public <T super DynaActionForm> DynaActionForm make(ResultSet result);
    Function<Connection, List<? super DynaActionForm>> select();
    Consumer<Connection> insert(DynaActionForm form);
    Consumer<Connection> update(DynaActionForm form);
    Consumer<Connection> delete(DynaActionForm form);

}
