package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Utils;

import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoParticipante.GANADOR;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.*;
import static ar.edu.ubp.das.src.utils.Constants.CUOTAS_MAX;
import static ar.edu.ubp.das.src.utils.Constants.CUOTAS_MIN;

class GetGanador extends SorteoStep {

    public GetGanador(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        try {
            final List<ParticipanteForm> participantes =
                    sorteoJobManager.getMsParticipanteDao().getParticipantes(CUOTAS_MIN, CUOTAS_MAX);
            insertParticipantes(participantes, sorteoForm.getId());

            if(participantes.isEmpty()){
                logSorteoFormDb(sorteoForm, COMPLETADO);
                throw new StepRunnerException("Sorteo cannot be executed because there are not participantes");
            }

            getGanador(participantes);

        } catch (final SQLException e) {
            e.printStackTrace();
            // en este caso en particular queremos que la proxima vez el sorteo se levante desde el step
            // pendiente_consumo debido a que si durante varios dias falla la seleccion del ganador
            // podría en algun momento quedar desactualizadas algunas concesionarias
            logSorteoFormDb(sorteoForm, PENDIENTE_CONSUMO, e.getMessage());
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
