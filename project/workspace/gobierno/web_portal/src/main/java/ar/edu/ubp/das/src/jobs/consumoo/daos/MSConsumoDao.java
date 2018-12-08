package ar.edu.ubp.das.src.jobs.consumoo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumoo.forms.ConsumoForm;

import java.sql.SQLException;
import java.util.List;

public class MSConsumoDao extends DaoImpl<ConsumoForm> {

    public MSConsumoDao() {
        super(ConsumoForm.class);
    }

    @Override
    public void insert(final ConsumoForm form) throws SQLException {

    }

    @Override
    public void update(final ConsumoForm form) throws SQLException {

    }

    @Override
    public void delete(final ConsumoForm form) throws SQLException {

    }

    @Override
    public List<ConsumoForm> select(final ConsumoForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final ConsumoForm form) throws SQLException {
        return false;
    }
}
