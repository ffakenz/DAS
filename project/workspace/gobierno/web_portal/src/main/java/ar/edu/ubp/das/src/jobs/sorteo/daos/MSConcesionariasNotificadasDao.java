package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcesionariasNotificadasForm;

import java.sql.SQLException;
import java.util.List;

public class MSConcesionariasNotificadasDao extends DaoImpl<ConcesionariasNotificadasForm> {

    public MSConcesionariasNotificadasDao() {
        super(ConcesionariasNotificadasForm.class);
    }


    public void insert(final ConcesionariasNotificadasForm form) throws SQLException {
        this.executeProcedure("dbo.log_concesionarias_notificadas(?,?)", form, "idSorteo", "idConcesionaria");
    }

    public void update(final ConcesionariasNotificadasForm form) throws SQLException {

    }

    public void delete(final ConcesionariasNotificadasForm form) throws SQLException {

    }

    public List<ConcesionariasNotificadasForm> select(final ConcesionariasNotificadasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionarias_notificadas(?)", form, "idSorteo");
    }

    public boolean valid(final ConcesionariasNotificadasForm form) throws SQLException {
        return false;
    }
}
