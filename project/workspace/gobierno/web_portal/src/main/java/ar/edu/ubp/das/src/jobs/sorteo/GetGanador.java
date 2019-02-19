package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Utils;

import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoParticipante.GANADOR;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.COMPLETADO;
import static ar.edu.ubp.das.src.utils.Constants.CUOTAS_MAX;
import static ar.edu.ubp.das.src.utils.Constants.CUOTAS_MIN;

class GetGanador extends SorteoStep {

    public GetGanador(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException {

        final List<ParticipanteForm> participantes =
                sorteoJobManager.getMsParticipanteDao().getParticipantes(CUOTAS_MIN, CUOTAS_MAX, sorteoForm.getFechaCreacion());

        if (participantes.isEmpty()) {
            sorteoForm.setEstado(COMPLETADO);
            throw new StepRunnerException("Sorteo cannot be executed because there are no participantes");
        }

        insertParticipantes(participantes, sorteoForm.getId()); // no tiene sentido ya que siempre esta siendo calculado en L#27
        getGanador(participantes); // el ganador podria ser un atributo del SorteoForm
        return sorteoForm;
    }

    private void getGanador(final List<ParticipanteForm> participantes) throws SQLException {
        final int indexGanador = Utils.getRandom(participantes.size());
        final ParticipanteForm ganador = participantes.get(indexGanador);
        ganador.setEstado(GANADOR);
        sorteoJobManager.getMsParticipanteDao().update(ganador);
    }

    private void insertParticipantes(final List<ParticipanteForm> participantes, final Long idSorteo) throws SQLException {
        for (final ParticipanteForm participante : participantes) {
            participante.setIdSorteo(idSorteo);
            sorteoJobManager.getMsParticipanteDao().insert(participante);
        }
    }
}
