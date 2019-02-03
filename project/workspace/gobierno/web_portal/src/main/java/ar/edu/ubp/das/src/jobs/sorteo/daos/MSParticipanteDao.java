package ar.edu.ubp.das.src.jobs.sorteo.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    @Override
    public boolean valid(final ParticipanteForm form) throws SQLException {
        return false;
    }

    public List<ParticipanteForm> getParticipantes(final Timestamp fechaEjecucion) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_participantes(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, fechaEjecucion);
        final List<ParticipanteForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    public Optional<ParticipanteForm> getUltimoGanador() throws SQLException {
        return executeQueryProcedure("dbo.get_ultimo_ganador")
                .stream()
                .findFirst();
    }
}
