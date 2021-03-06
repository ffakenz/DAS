package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MSParticipanteDao extends DaoImpl<ParticipanteForm> {

    public MSParticipanteDao() {
        super(ParticipanteForm.class);
    }


    @Override
    public void insert(final ParticipanteForm form) throws SQLException {
        executeProcedure("dbo.log_participante(?,?,?,?,?)", form,
                "idSorteo", "idPlan", "idConcesionaria", "idConsumer", "idVehiculo");
    }

    @Override
    public void update(final ParticipanteForm form) throws SQLException {
        executeProcedure("dbo.update_estado_participante(?,?,?,?,?,?)", form,
                "idSorteo", "idPlan", "idConcesionaria", "idConsumer", "idVehiculo", "estado");
    }

    @Override
    public void delete(final ParticipanteForm form) throws SQLException {

    }

    @Override
    public List<ParticipanteForm> select(final ParticipanteForm form) throws SQLException {
        return null;
    }

    public List<ParticipanteForm> selectBySorteo(final Long sorteoId) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_participantes_sorteo(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, sorteoId);
        final List<ParticipanteForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    @Override
    public boolean valid(final ParticipanteForm form) throws SQLException {
        return false;
    }

    public List<ParticipanteForm> getParticipantes(final Integer cuotasMin,
                                                   final Integer cuotasMax,
                                                   final Date fechaCreacion) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_participantes(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, cuotasMin);
        this.setParameter(2, cuotasMax);
        this.setParameter(3, fechaCreacion);
        final List<ParticipanteForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    public Optional<ParticipanteForm> getUltimoGanador() throws SQLException {
        return executeQueryProcedure("dbo.get_ultimo_ganador")
                .stream()
                .findFirst();
    }

    public Optional<ParticipanteForm> getGanadorBySorteo(final Long sorteoId) throws SQLException {

        this.connect();
        this.setProcedure("dbo.get_ganador_by_sorteo_id(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, sorteoId);
        final List<ParticipanteForm> result = this.executeQuery();
        this.disconnect();
        return result.stream().findFirst();
    }
}
