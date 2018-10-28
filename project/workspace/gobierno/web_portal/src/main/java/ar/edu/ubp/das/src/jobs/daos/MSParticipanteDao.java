package ar.edu.ubp.das.src.jobs.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.jobs.forms.ParticipanteForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSParticipanteDao extends DaoImpl<ParticipanteForm> {

    public MSParticipanteDao() {
        super(ParticipanteForm.class);
    }


    @Override
    public void insert(final ParticipanteForm form) throws SQLException {
        executeProcedure("dbo.insert_participante(?,?)", form, "idSorteo", "idPlan");
    }

    @Override
    public void update(final ParticipanteForm form) throws SQLException {
        executeProcedure("dbo.update_participante(?,?,?)", form, "idSorteo", "idPlan",
                "estado");
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

    public Optional<ParticipanteForm> getUltimoGanador() throws SQLException {
        return executeQueryProcedure("dbo.get_ultimo_ganador")
                .stream()
                .findFirst();
    }
}
