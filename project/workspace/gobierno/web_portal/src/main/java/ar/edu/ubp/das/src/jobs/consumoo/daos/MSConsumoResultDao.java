package ar.edu.ubp.das.src.jobs.consumoo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.consumoo.forms.ConsumoResultForm;

import java.sql.SQLException;
import java.util.List;

public class MSConsumoResultDao extends DaoImpl<ConsumoResultForm> {

    public MSConsumoResultDao() {
        super(ConsumoResultForm.class);
    }

    @Override
    public void insert(final ConsumoResultForm form) throws SQLException {
        this.executeProcedure("dbo.log_consumo_result(?, ?, ?, ?)", form,
                "idConcesionaria", "idConsumo", "description", "result");
    }

    @Override
    public void update(final ConsumoResultForm form) throws SQLException {

    }

    @Override
    public void delete(final ConsumoResultForm form) throws SQLException {

    }

    @Override
    public List<ConsumoResultForm> select(final ConsumoResultForm form) throws SQLException {
        return null;
    }

    @Override
    public boolean valid(final ConsumoResultForm form) throws SQLException {
        return false;
    }
}
