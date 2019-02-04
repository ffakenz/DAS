package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcesionariasNotificadasForm;

import java.sql.SQLException;
import java.util.List;

public class MSConcesionariasNotificadasDao extends DaoImpl<ConcesionariasNotificadasForm> {

    public MSConcesionariasNotificadasDao() {
        super(ConcesionariasNotificadasForm.class);
    }


    @Override
    public void insert(ConcesionariasNotificadasForm form) throws SQLException {
        this.executeProcedure("dbo.log_concesionarias_notificadas(?,?)", form, "idSorteo", "idConcesionaria");
    }

    @Override
    public void update(ConcesionariasNotificadasForm form) throws SQLException {

    }

    @Override
    public void delete(ConcesionariasNotificadasForm form) throws SQLException {

    }

    @Override
    public List<ConcesionariasNotificadasForm> select(ConcesionariasNotificadasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionarias_notificadas(?)", form, "idSorteo");
    }

    @Override
    public boolean valid(ConcesionariasNotificadasForm form) throws SQLException {
        return false;
    }
}
