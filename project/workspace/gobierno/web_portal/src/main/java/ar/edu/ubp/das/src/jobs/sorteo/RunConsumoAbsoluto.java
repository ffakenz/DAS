package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.ConsumoAbsoluto;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CONSUMO;

class RunConsumoAbsoluto extends SorteoStep {

    public RunConsumoAbsoluto(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {

        final ConsumoAbsoluto consumoAbsoluto = new ConsumoAbsoluto(datasourceConfig, clientFactoryAdapter);

        if (!consumoAbsoluto.ejecutar(sorteoForm.getId())) {
            logSorteoFormDb(sorteoForm, PENDIENTE_CONSUMO, "Consumo Absoluto Fail");
            throw new StepRunnerException(name);
        }

        name =
        return sorteoForm;
    }
}
