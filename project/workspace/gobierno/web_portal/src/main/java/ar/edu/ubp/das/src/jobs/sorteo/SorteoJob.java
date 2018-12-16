package ar.edu.ubp.das.src.jobs.sorteo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class SorteoJob implements Job {

    // from web_portal
//    private ConcesionariasManager concesionariasManager;
//    private ConfigurarConcesionariaManager configurarConcesionariaManager;
//    private ConsumerManager consumerManager;
//    private CuotasManager cuotasManager;
//    private EstadoCuentasManager estadoCuentasManager;
//
//    // inners
//    private MSParticipanteDao msParticipanteDao;
//    private MSSorteoDao msSorteoDao;
//
//    private IClientFactory clientFactory;
//    private ISorteoInvariantsHolder sorteoInvariantsHolder;
//
//    public SorteoJob(final DatasourceConfig datasourceConfig, final IClientFactory clientFactory, final ISorteoInvariantsHolder sorteoInvariantsHolder) {
//
//        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
//        msConcesionariasDao.setDatasource(datasourceConfig);
//        this.concesionariasManager = new ConcesionariasManager(msConcesionariasDao);
//
//        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
//        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
//        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);
//
//        final MSConsumerDao msConsumerDao = new MSConsumerDao();
//        msConsumerDao.setDatasource(datasourceConfig);
//        this.consumerManager = new ConsumerManager(msConsumerDao);
//
//        final MSCuotasDao msCuotasDao = new MSCuotasDao();
//        msCuotasDao.setDatasource(datasourceConfig);
//        this.cuotasManager = new CuotasManager(msCuotasDao);
//
//        final MSEstadoCuentasDao msEstadoCuentasDao = new MSEstadoCuentasDao();
//        msEstadoCuentasDao.setDatasource(datasourceConfig);
//        this.estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);
//
//        this.msParticipanteDao = new MSParticipanteDao();
//        this.msParticipanteDao.setDatasource(datasourceConfig);
//
//        this.msSorteoDao = new MSSorteoDao();
//        this.msSorteoDao.setDatasource(datasourceConfig);
//
//        this.clientFactory = clientFactory;
//        this.sorteoInvariantsHolder = sorteoInvariantsHolder;
//    }
//
//    public Boolean verificarCancelacionCuenta() throws JobExecutionException {
//        try {
//            final Optional<ParticipanteForm> ultimoGanador = msParticipanteDao.getUltimoGanador();
//            if (!ultimoGanador.isPresent()) {
//                return false;
//            }
//
//            final ParticipanteForm ganador = ultimoGanador.get();
//
//            // selectEstadoCuentasById cannot fail if there is a winner
//            final EstadoCuentasForm estadoCuentaGanador =
//                    estadoCuentasManager.getDao().selectEstadoCuentasById(ganador.getIdPlan()).get();
//
//            // concesionaria selectById cannot fail if there is a winner
//            final ConcesionariaForm concesionariaGanador =
//                    concesionariasManager.getDao().selectById(estadoCuentaGanador.getId()).get();
//
//            // getClientFromConcesionaria cannot fail if there is a winner
//            final ConcesionariaServiceContract client =
//                    getClientFromConcesionaria(concesionariaGanador.getId()).get();
//
//            final PlanBean planBeanResponse = client.consultarPlan(ultimoGanador.get().getIdPlan());
//
//            if (sorteoInvariantsHolder.isPlanCancelado(planBeanResponse)) {
//                ganador.setEstado("ganador");
//                msParticipanteDao.update(ganador);
//            }
//
//            return true;
//
//        } catch (final SQLException ex) {
//            throw new JobExecutionException(ex);
//        }
//    }
//
//    private Optional<ConcesionariaServiceContract> getClientFromConcesionaria(final Long concesionariaId) throws SQLException {
//        final List<ConfigurarConcesionariaForm> paramsConfigTecnoGanador =
//                configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(concesionariaId);
//
//        if (paramsConfigTecnoGanador.isEmpty()) {
//            return Optional.empty();
//        }
//
//        // start get client based on config tecno
//        final String configTecno = paramsConfigTecnoGanador.get(0).getConfigTecno();
//        final Map<String, String> clientCall =
//                paramsConfigTecnoGanador.stream().collect(
//                        Collectors.toMap(ConfigurarConcesionariaForm::getConfigParam, ConfigurarConcesionariaForm::getValue)
//                );
//        return clientFactory.getClientFor(configTecno, clientCall);
//    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {
        System.out.println("SORTEO");
    }
}
