package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcesionariasNotificadasForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConcesionariasNotificadasDao extends DaoImpl<ConcesionariasNotificadasForm> {

    public MSConcesionariasNotificadasDao() {
        super(ConcesionariasNotificadasForm.class);
    }


    public void insert(final ConcesionariasNotificadasForm form) throws SQLException {
        this.executeProcedure("dbo.log_concesionarias_notificadas(?,?)", form, "idSorteo", "idConcesionaria");
    }

    public List<ConcesionariasNotificadasForm> select(final Long idSorteo) throws SQLException {

        this.connect();
        this.setProcedure("dbo.get_concesionarias_notificadas(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, idSorteo);
        final List<ConcesionariasNotificadasForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    public void update(final ConcesionariasNotificadasForm form) throws SQLException {

    }

    public void delete(final ConcesionariasNotificadasForm form) throws SQLException {

    }

    public boolean valid(final ConcesionariasNotificadasForm form) throws SQLException {
        return false;
    }

    @Override
    public List<ConcesionariasNotificadasForm> select() throws SQLException {
        return null;
    }

    @Override
    public List<ConcesionariasNotificadasForm> select(ConcesionariasNotificadasForm form) throws SQLException {
        return null;
    }
}
