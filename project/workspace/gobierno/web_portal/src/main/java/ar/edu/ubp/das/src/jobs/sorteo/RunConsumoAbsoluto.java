package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.src.jobs.consumo_absoluto.ConsumoAbsoluto;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CONSUMO;
import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_SELECCION_GANADOR;

class RunConsumoAbsoluto extends SorteoStep {
    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        final ConsumoAbsoluto consumoAbsoluto =
                new ConsumoAbsoluto(datasourceConfig, clientFactoryAdapter);


        if (!consumoAbsoluto.ejecutar(sorteoForm.getId())) {
            logSorteoFormDb(sorteoForm, PENDIENTE_CONSUMO, "Consumo Absoluto Fail");
            throw new StepRunnerException(name);
        }

        logSorteoFormDb(sorteoForm, PENDIENTE_SELECCION_GANADOR);
        return sorteoForm;
    }
}
