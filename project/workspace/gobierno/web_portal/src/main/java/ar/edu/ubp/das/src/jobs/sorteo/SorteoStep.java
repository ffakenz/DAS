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
import clients.factory.ClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Optional;

abstract class SorteoStep {

    protected static final Logger log = LoggerFactory.getLogger(SorteoStep.class);

    protected abstract SorteoForm runContext(final SorteoForm sorteoForm) throws StepRunnerException;

    protected String name;
    protected SorteoStep next;
    protected SorteoStep father;
    protected DatasourceConfig datasourceConfig;
    protected SorteoJobManager sorteoJobManager;
    protected ClientFactoryAdapter clientFactoryAdapter;
    protected ConfigurarConcesionariaManager configurarConcesionariaManager;
    protected ConcesionariasManager concesionariasManager;


    public SorteoStep() {
        this.name = this.getClass().getSimpleName();
        datasourceConfig = new DatasourceConfig();
        this.sorteoJobManager = new SorteoJobManager(datasourceConfig);
        this.clientFactoryAdapter = new ClientFactoryAdapter(ClientFactory.getInstance());
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

    public SorteoForm executeOnRoot(final String stepNameToRun, final SorteoForm sorteoForm) throws StepRunnerException {
        return this.root().execute(stepNameToRun, sorteoForm);
    }

    private SorteoForm execute(final String stepNameToRun, final SorteoForm sorteoForm) throws StepRunnerException {

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

    protected void logSorteoFormDb(final SorteoForm sorteoForm, final EstadoSorteo estadoSorteo, final String errorMsg) throws StepRunnerException {

        log.error("[exception:{}]", errorMsg);

        sorteoForm.setEstado(estadoSorteo);
        try {
            sorteoJobManager.getMsSorteoDao().update(sorteoForm);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new StepRunnerException(name);
        }
    }

    protected void logSorteoFormDb(final SorteoForm sorteoForm, final EstadoSorteo estadoSorteo) throws StepRunnerException {

        sorteoForm.setEstado(estadoSorteo);
        try {
            sorteoJobManager.getMsSorteoDao().update(sorteoForm);
        } catch (final SQLException e) {
            e.printStackTrace();
            throw new StepRunnerException(name);
        }
    }

    protected ConcesionariaServiceContract getHttpClient(final Long concesionariaId) throws StepRunnerException {
        final Optional<ConcesionariaServiceContract> clientForConcesionaria =
                clientFactoryAdapter.getClientForConcesionaria(concesionariaId, configurarConcesionariaManager);

        if (!clientForConcesionaria.isPresent())
            throw new StepRunnerException(String.format("Get client for concesionaria %s fail", concesionariaId));

        return clientForConcesionaria.get();
    }
}
