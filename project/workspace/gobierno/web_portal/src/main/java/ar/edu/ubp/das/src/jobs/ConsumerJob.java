package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.model.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.model.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.model.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.model.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.daos.MSParticipanteDao;
import ar.edu.ubp.das.src.jobs.daos.MSSorteoDao;
import ar.edu.ubp.das.src.jobs.sorteo.ISorteoInvariantsHolder;
import clients.IClientFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class ConsumerJob implements Job {

    // from web_portal
    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private ConsumerManager consumerManager;
    private CuotasManager cuotasManager;
    private EstadoCuentasManager estadoCuentasManager;

    // inners
    private MSParticipanteDao msParticipanteDao;
    private MSSorteoDao msSorteoDao;

    private IClientFactory clientFactory;
    private ISorteoInvariantsHolder sorteoInvariantsHolder;

    public ConsumerJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory) {

        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        this.concesionariasManager = new ConcesionariasManager(msConcesionariasDao);

        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);

        final MSConsumerDao msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(datasourceConfig);
        this.consumerManager = new ConsumerManager(msConsumerDao);

        final MSCuotasDao msCuotasDao = new MSCuotasDao();
        msCuotasDao.setDatasource(datasourceConfig);
        this.cuotasManager = new CuotasManager(msCuotasDao);

        final MSEstadoCuentasDao msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
        this.estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);

        this.msParticipanteDao = new MSParticipanteDao();
        this.msParticipanteDao.setDatasource(datasourceConfig);

        this.msSorteoDao = new MSSorteoDao();
        this.msSorteoDao.setDatasource(datasourceConfig);

        this.clientFactory = clientFactory;
        this.sorteoInvariantsHolder = sorteoInvariantsHolder;
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        System.out.println("CONSUMER");
    }


}