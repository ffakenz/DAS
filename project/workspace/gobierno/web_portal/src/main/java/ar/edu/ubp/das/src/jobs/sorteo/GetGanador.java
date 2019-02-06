package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Utils;

import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoParticipante.GANADOR;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CANCELACION;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_SELECCION_GANADOR;
import static ar.edu.ubp.das.src.utils.Constants.CUOTAS_MAX;
import static ar.edu.ubp.das.src.utils.Constants.CUOTAS_MIN;

class GetGanador extends SorteoStep {

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            final List<ParticipanteForm> participantes =
                    sorteoJobManager.getMsParticipanteDao().getParticipantes(CUOTAS_MIN, CUOTAS_MAX);
            insertParticipantes(participantes, sorteoForm.getId());
            getGanador(participantes);

        } catch (final SQLException e) {
            e.printStackTrace();
            logSorteoFormDb(sorteoForm, PENDIENTE_SELECCION_GANADOR, e.getMessage());
            throw new StepRunnerException(name);
        }

        logSorteoFormDb(sorteoForm, PENDIENTE_CANCELACION);
        return sorteoForm;
    }

    private void insertParticipantes(final List<ParticipanteForm> participantes, final Long idSorteo) throws SQLException {
        for (final ParticipanteForm participante : participantes) {
            participante.setIdSorteo(idSorteo);
            sorteoJobManager.getMsParticipanteDao().insert(participante);
        }
    }

    private void getGanador(final List<ParticipanteForm> participantes) throws SQLException {
        final int indexGanador = Utils.getRandom(participantes.size());
        final ParticipanteForm ganador = participantes.get(indexGanador);
        ganador.setEstado(GANADOR);
        sorteoJobManager.getMsParticipanteDao().update(ganador);
    }
}
