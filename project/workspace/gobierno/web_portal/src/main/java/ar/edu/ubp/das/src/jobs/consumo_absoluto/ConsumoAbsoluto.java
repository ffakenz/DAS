package ar.edu.ubp.das.src.jobs.consumo_absoluto;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.managers.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.managers.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.managers.EstadoCuentasManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.daos.MSConsumoAbsolutoDao;
import ar.edu.ubp.das.src.jobs.consumo_absoluto.forms.ConsumoAbsolutoForm;
import ar.edu.ubp.das.src.utils.Constants;
import beans.CuotaBean;
import beans.PlanBean;
import clients.ConcesionariaServiceContract;
import clients.responses.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ar.edu.ubp.das.src.jobs.consumo.forms.EstadoConsumo.SUCCESS;

// TODO: refactor name "ConsumoAbsoluto" to "ConsumoSorteo" <3
public class ConsumoAbsoluto {

    private static final Logger log = LoggerFactory.getLogger(ConsumoAbsoluto.class);

    private ConcesionariasManager concesionariasManager;
    private ConfigurarConcesionariaManager configurarConcesionariaManager;
    private EstadoCuentasManager estadoCuentasManager;
    private CuotasManager cuotasManager;
    private ClientFactoryAdapter clientFactoryAdapter;
    private ConsumerManager consumerManager;
    private MSConsumoAbsolutoDao msConsumoAbsolutoDao;


    public ConsumoAbsoluto(final DatasourceConfig datasourceConfig,
                           final ClientFactoryAdapter clientFactoryAdapter) {

        final MSConsumerDao msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(datasourceConfig);
        this.consumerManager = new ConsumerManager(msConsumerDao);

        final MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        this.concesionariasManager = new ConcesionariasManager(msConcesionariasDao);

        final MSConfigurarConcesionariaDao msConfigurarConcesionariaDao = new MSConfigurarConcesionariaDao();
        msConfigurarConcesionariaDao.setDatasource(datasourceConfig);
        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);

        final MSEstadoCuentasDao msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
        this.estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);

        final MSCuotasDao msCuotasDao = new MSCuotasDao();
        msCuotasDao.setDatasource(datasourceConfig);
        this.cuotasManager = new CuotasManager(msCuotasDao);

        this.clientFactoryAdapter = clientFactoryAdapter;

        this.msConsumoAbsolutoDao = new MSConsumoAbsolutoDao();
        msConcesionariasDao.setDatasource(datasourceConfig);

    }


    // TODO : Change Return Time to DTO Response
    public boolean ejecutar(final Long sorteoId) {

        final ConsumoAbsolutoForm consumoAbsolutoForm = new ConsumoAbsolutoForm();
        consumoAbsolutoForm.setFecha(Timestamp.from(Instant.now()));
        consumoAbsolutoForm.setIdSorteo(sorteoId);
        final List<ConcesionariaForm> aprobadas = this.getAllConcesionariasAprobadasDesactualizadas(consumoAbsolutoForm);
        for (final ConcesionariaForm aprobada : aprobadas) {
            final Optional<ConcesionariaServiceContract> client = this.getClient(consumoAbsolutoForm, aprobada.getId());
            client.ifPresent(cli -> {
                final List<EstadoCuentasForm> estadoCuentasForms = this.getAllEstadoCuentasByConcesionaria(consumoAbsolutoForm, aprobada.getId());
                for (final EstadoCuentasForm estadoCuentasForm : estadoCuentasForms) {
                    final String rqstId = UUID.randomUUID().toString();
                    final Optional<PlanBean> planBean = this.consultarPlan(cli, consumoAbsolutoForm, estadoCuentasForm, rqstId);
                    planBean.ifPresent(pb -> {
                        // insert(planBean, consumo absoluto plan aprobada id);
                        this.updateDb(consumoAbsolutoForm, estadoCuentasForm, rqstId, pb);
                    });
                }
                if (!estadoCuentasForms.isEmpty()) {
                    // TODO <- Improve this with comment below
                    // si todos los estados de cuenta resultaron exitosos => mark [consumo absoluto aprobada] as success => [query]
                    log.info("[ConsumoAbsoluto.ejecutar][SUCCEDED aprobada {}]", aprobada.getId());
                    consumoAbsolutoForm.setEstado(SUCCESS);
                    consumoAbsolutoForm.setCause("aprobada");
                    consumoAbsolutoForm.setConcesionariaId(aprobada.getId());
                    logConsumoAbsolutoForm(consumoAbsolutoForm);
                }
            });
        }
        if (!aprobadas.isEmpty()) {
            // TODO <- Improve this with comment below
            // si todas las aprobadas resultaron exitosas => mark [consumo absoluto] as success => [query]
            log.info("[ConsumoAbsoluto.ejecutar][SUCCESS]");
            consumoAbsolutoForm.setEstado(SUCCESS);
            consumoAbsolutoForm.setCause("job");
            logConsumoAbsolutoForm(consumoAbsolutoForm);
        } else {
            consumoAbsolutoForm.setEstado(SUCCESS);
        }

        return consumoAbsolutoForm.getEstadoConsumo().equals(SUCCESS);
    }

    private List<ConcesionariaForm> getAllConcesionariasAprobadasDesactualizadas(final ConsumoAbsolutoForm consumoAbsolutoForm) {
        try {
            return concesionariasManager.getDao().selectAprobadasDesactualizadas(-5); // Magic Number [Franco:I love you Mariela]
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[ConsumoAbsoluto.ejecutar][FAILED getAllConcesionariasAprobadasDesactualizadas]");
            consumoAbsolutoForm.setEstado("FAILED");
            consumoAbsolutoForm.setCause("getAllConcesionariasAprobadasDesactualizadas");
            logConsumoAbsolutoForm(consumoAbsolutoForm);
            return new ArrayList<>();
        }
    }

    private Optional<ConcesionariaServiceContract> getClient(final ConsumoAbsolutoForm consumoAbsolutoForm, final Long concesionariaId) {
        final Optional<ConcesionariaServiceContract> clientForConcesionaria = clientFactoryAdapter.getClientForConcesionaria(concesionariaId, configurarConcesionariaManager);
        if (!clientForConcesionaria.isPresent()) {
            log.error("[ConsumoAbsoluto.ejecutar][FAILED getClientForConcesionaria][ConcesionariaId {}]", concesionariaId);
            consumoAbsolutoForm.setEstado("FAILED");
            consumoAbsolutoForm.setCause("getClientForConcesionaria");
            consumoAbsolutoForm.setConcesionariaId(concesionariaId);
            logConsumoAbsolutoForm(consumoAbsolutoForm);
            return Optional.empty();
        }
        return clientForConcesionaria;
    }

    private List<EstadoCuentasForm> getAllEstadoCuentasByConcesionaria(final ConsumoAbsolutoForm consumoAbsolutoForm, final Long concesionariaId) {
        try {
            return estadoCuentasManager.getDao().selectEstadoCuentasByConcesionariaId(concesionariaId);
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[EJECUTAR][FAILED selectEstadoCuentasByConcesionariaId][ConcesionariaId {}]", concesionariaId);
            consumoAbsolutoForm.setEstado("FAILED");
            consumoAbsolutoForm.setCause("selectEstadoCuentasByConcesionariaId");
            consumoAbsolutoForm.setConcesionariaId(concesionariaId);
            logConsumoAbsolutoForm(consumoAbsolutoForm);
            return new ArrayList<>();
        }
    }

    private Optional<PlanBean> consultarPlan(final ConcesionariaServiceContract client,
                                             final ConsumoAbsolutoForm consumoAbsolutoForm,
                                             final EstadoCuentasForm estadoCuentasForm,
                                             final String rqstId) {
        try {
            final PlanBean planBean = client.consultarPlan(Constants.IDENTIFICADOR, estadoCuentasForm.getNroPlanConcesionaria());
            return Optional.of(planBean);
        } catch (final ClientException e) {
            e.printStackTrace();
            log.error("[EJECUTAR][FAILED consultarPlan][ConcesionariaId {}][PlanId {}]",
                    estadoCuentasForm.getConcesionariaId(), estadoCuentasForm.getNroPlanConcesionaria());
            consumoAbsolutoForm.setEstado("FAILED");
            consumoAbsolutoForm.setCause("consultarPlan");
            consumoAbsolutoForm.setConcesionariaId(estadoCuentasForm.getConcesionariaId());
            consumoAbsolutoForm.setEstadoCuentaId(estadoCuentasForm.getId());
            consumoAbsolutoForm.setPlanId(estadoCuentasForm.getNroPlanConcesionaria());
            consumoAbsolutoForm.setIdRequestResp(rqstId);
            logConsumoAbsolutoForm(consumoAbsolutoForm);
            return Optional.empty();
        }
    }

    /* DESNORMALIZER */
    private void updateDb(final ConsumoAbsolutoForm consumoAbsolutoForm,
                          final EstadoCuentasForm estadoCuentasForm,
                          final String rqstId,
                          final PlanBean update) {
        try {
            final EstadoCuentasForm upserted = updateEstadoCuentaDb(update, estadoCuentasForm.getConcesionariaId());
            updateCuotaDb(update, upserted.getId());
            consumoAbsolutoForm.setEstado("SUCCESS");
            consumoAbsolutoForm.setCause("updateDb");
            consumoAbsolutoForm.setConcesionariaId(estadoCuentasForm.getConcesionariaId());
            consumoAbsolutoForm.setEstadoCuentaId(estadoCuentasForm.getId());
            consumoAbsolutoForm.setPlanId(estadoCuentasForm.getNroPlanConcesionaria());
            consumoAbsolutoForm.setIdRequestResp(rqstId);
            logConsumoAbsolutoForm(consumoAbsolutoForm);
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[EJECUTAR][FAILED updateDb][ConcesionariaId {}][PlanId {}][RQST {}]",
                    estadoCuentasForm.getConcesionariaId(), estadoCuentasForm.getNroPlanConcesionaria(), rqstId);
            consumoAbsolutoForm.setEstado("FAILED");
            consumoAbsolutoForm.setCause("updateDb");
            consumoAbsolutoForm.setConcesionariaId(estadoCuentasForm.getConcesionariaId());
            consumoAbsolutoForm.setEstadoCuentaId(estadoCuentasForm.getId());
            consumoAbsolutoForm.setPlanId(estadoCuentasForm.getNroPlanConcesionaria());
            consumoAbsolutoForm.setIdRequestResp(rqstId);
            logConsumoAbsolutoForm(consumoAbsolutoForm);
        }
    }

    private EstadoCuentasForm updateEstadoCuentaDb(final PlanBean update, final Long concesionariaId) throws SQLException {
        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        estadoCuenta.setConcesionariaId(concesionariaId);
        estadoCuenta.setNroPlanConcesionaria(update.getPlanId());
        estadoCuenta.setEstado(update.getPlanEstado());
        return estadoCuentasManager.getDao().upsert(estadoCuenta); // will be always update
    }

    private void updateCuotaDb(final PlanBean update, final Long estadoCuentaId) throws SQLException {

        for (final CuotaBean cuotaBean : update.getCuotas()) {
            final CuotasForm cuota = new CuotasForm();
            cuota.setEstadoCuentaId(estadoCuentaId);
            cuota.setNroCuota(cuotaBean.getCuotaNroCuota());
            cuota.setFechaVencimiento(cuotaBean.getCuotaFechaVencimiento());
            cuota.setMonto(cuotaBean.getCuotaMonto());
            cuota.setFechaPago(cuotaBean.getCuotaFechaPago());
            cuota.setFechaAltaConcesionaria(cuotaBean.getCuotaFechaAlta());
            cuotasManager.getDao().upsert(cuota);
        }
    }

    /* LOGGERS */
    public void logConsumoAbsolutoForm(final ConsumoAbsolutoForm consumoAbsolutoForm) {
        if (consumoAbsolutoForm.isForConcesionariaPlan()) {
            msConsumoAbsolutoDao.insertConsumoAbsolutoConcesionaria(consumoAbsolutoForm);
        } else if (consumoAbsolutoForm.isForConcesionaria()) {
            msConsumoAbsolutoDao.insertConsumoAbsolutoConcesionariaPlan(consumoAbsolutoForm);
        } else {
            msConsumoAbsolutoDao.insertConsumoAbsoluto(consumoAbsolutoForm);
        }
    }
}