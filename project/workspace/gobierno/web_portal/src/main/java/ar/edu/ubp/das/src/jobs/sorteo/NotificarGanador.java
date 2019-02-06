package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.PENDIENTE_NOTIFICACION_CONCESIONARIAS;

class NotificarGanador extends SorteoStep {

    public NotificarGanador(DatasourceConfig datasourceConfig, ClientFactoryAdapter clientFactoryAdapter) {
        super(datasourceConfig, clientFactoryAdapter);
    }

    @Override
    public SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException {
        //TODO: adding api to send email
        System.out.println("MAIL ENVIADO");
        sorteoForm.setEstado(PENDIENTE_NOTIFICACION_CONCESIONARIAS);
        return sorteoForm;
    }
}
