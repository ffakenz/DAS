package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ConcPendienteNotificacionForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConcPendienteNotificacionDao extends DaoImpl<ConcPendienteNotificacionForm> {

    public MSConcPendienteNotificacionDao() {
        super(ConcPendienteNotificacionForm.class);
    }


    public void insert(final ConcPendienteNotificacionForm form) throws SQLException {
        this.executeProcedure("dbo.log_conc_pendiente_notificacion(?,?)", form, "idSorteo", "idConcesionaria");
    }

    public List<ConcPendienteNotificacionForm> selectBySorteoId(final Long idSorteo) throws SQLException {

        this.connect();
        this.setProcedure("dbo.get_conc_pendiente_notificacion(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, idSorteo);
        final List<ConcPendienteNotificacionForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    public void update(final ConcPendienteNotificacionForm form) throws SQLException {

    }

    public void delete(final ConcPendienteNotificacionForm form) throws SQLException {

        this.executeProcedure("dbo.delete_conc_pendiente_notificacion(?,?)", form,
                "idSorteo", "idConcesionaria");
    }

    public boolean valid(final ConcPendienteNotificacionForm form) throws SQLException {
        return false;
    }

    @Override
    public List<ConcPendienteNotificacionForm> select() throws SQLException {
        return null;
    }

    @Override
    public List<ConcPendienteNotificacionForm> select(final ConcPendienteNotificacionForm form) throws SQLException {
        return null;
    }
}
