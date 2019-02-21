package ar.edu.ubp.das.src.queries.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.queries.forms.EstadoCuentaDashForm;

import java.sql.SQLException;
import java.util.List;

public class MSEstadoCuentaDashDao extends DaoImpl<EstadoCuentaDashForm> {

    public MSEstadoCuentaDashDao() {
        super(EstadoCuentaDashForm.class);
    }

    @Override
    public void insert(final EstadoCuentaDashForm form) throws SQLException {

    }

    @Override
    public void update(final EstadoCuentaDashForm form) throws SQLException {

    }

    @Override
    public void delete(final EstadoCuentaDashForm form) throws SQLException {

    }

    @Override
    public List<EstadoCuentaDashForm> select(final EstadoCuentaDashForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.q_estado_cuentas_dash");
    }

    @Override
    public boolean valid(final EstadoCuentaDashForm form) throws SQLException {
        return false;
    }
}



