package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.ConsumoAbsoluto;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import java.sql.SQLException;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_CONSUMO;


class RunConsumoAbsoluto extends SorteoStep {

    public RunConsumoAbsoluto(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException {

        final ConsumoAbsoluto consumoAbsoluto = new ConsumoAbsoluto(datasourceConfig, clientFactoryAdapter);

        if (!consumoAbsoluto.ejecutar(sorteoForm)) {
            sorteoForm.setEstado(PENDIENTE_CONSUMO);
            throw new StepRunnerException(name);
        }

        // succeful
        return sorteoForm; // pq sabe cual es su next step puede continuar
    }
}
