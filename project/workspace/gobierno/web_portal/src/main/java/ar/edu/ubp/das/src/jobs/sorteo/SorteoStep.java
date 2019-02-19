package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import clients.ConcesionariaServiceContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

abstract class SorteoStep {

    protected static final Logger log = LoggerFactory.getLogger(SorteoStep.class);

    protected abstract SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException, SQLException;

    protected String name;
    protected SorteoStep next;
    protected SorteoStep father;
    protected DatasourceConfig datasourceConfig;
    protected SorteoJobManager sorteoJobManager;
    protected ClientFactoryAdapter clientFactoryAdapter;
    protected ConfigurarConcesionariaManager configurarConcesionariaManager;
    protected ConcesionariasManager concesionariasManager;


    public SorteoStep(final DatasourceConfig datasourceConfig, final ClientFactoryAdapter clientFactoryAdapter) {
        this.name = this.getClass().getSimpleName();

        this.datasourceConfig = datasourceConfig;
        this.clientFactoryAdapter = clientFactoryAdapter;

        this.sorteoJobManager = new SorteoJobManager(datasourceConfig);

        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);

        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        concesionariasManager = new ConcesionariasManager(msConcesionariasDao);
    }

    public SorteoStep then(final SorteoStep next) {
        this.next = next;
        next.father = this;
        return next;
    }


    public SorteoForm executeOnRoot(final HashMap<EstadoSorteo, String> stepsByEstado, final SorteoForm sorteoForm) throws StepRunnerException, SQLException {
        final String stateToExecute = stepsByEstado.get(sorteoForm.getEstadoSorteo());
        return this.root().execute(stateToExecute, sorteoForm);
    }

    private SorteoForm execute(final String stepNameToRun, final SorteoForm sorteoForm) throws StepRunnerException, SQLException {
        if (name.equals(stepNameToRun)) {
            System.out.println("START STEP: " + name);
            final SorteoForm result = runContext(sorteoForm);
            System.out.println("FINISH STEP: " + name);
            if (next != null)
                return next.execute(next.name, result);
            return result;
        } else if (next != null) {
            return next.execute(stepNameToRun, sorteoForm);
        } else {
            return sorteoForm;
        }
    }

    private SorteoStep root() {
        if (this.father != null) {
            return this.father.root();
        } else {
            return this;
        }
    }

    protected void logSorteoFormDb(final SorteoForm sorteoForm) throws SQLException {
        sorteoJobManager.getMsSorteoDao().update(sorteoForm);
    }

    protected ConcesionariaServiceContract getHttpClient(final Long concesionariaId) throws StepRunnerException {
        final Optional<ConcesionariaServiceContract> clientForConcesionaria =
                clientFactoryAdapter.getClientForConcesionaria(concesionariaId, configurarConcesionariaManager);

        if (!clientForConcesionaria.isPresent())
            throw new StepRunnerException(String.format("Get client for concesionaria %s fail", concesionariaId));

        return clientForConcesionaria.get();
    }
}
