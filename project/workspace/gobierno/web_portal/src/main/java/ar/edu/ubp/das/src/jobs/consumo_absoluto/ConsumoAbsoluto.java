package ar.edu.ubp.das.src.jobs.consumo_absoluto;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
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
    public void ejecutar() {
        final ConsumoAbsolutoForm consumoAbsolutoForm = new ConsumoAbsolutoForm();
        consumoAbsolutoForm.setFecha(Timestamp.from(Instant.now()));
        // GET ALL CONCESIONARIAS APROBADAS
        List<ConcesionariaForm> aprobadas = new ArrayList<>();
        try {
            aprobadas = concesionariasManager.getDao().selectAprobadas();
        } catch (final SQLException e) {
            e.printStackTrace();
            log.error("[ConsumoAbsoluto.ejecutar][FAILED selectAprobadas]");
            consumoAbsolutoForm.setEstado("FAILED");
            consumoAbsolutoForm.setCause("selectAprobadas");
            logConsumoAbsolutoForm(consumoAbsolutoForm);
        }
        // UPDATE ALL CONCESIONARIAS APROBADAS
        for (final ConcesionariaForm aprobada : aprobadas) {
            // OBTAIN CLIENT FOR CONCESIONARIA
            final Optional<ConcesionariaServiceContract> client = getClient(aprobada.getId());
            if (!client.isPresent()) {
                log.error("[ConsumoAbsoluto.ejecutar][FAILED getClientForConcesionaria][ConcesionariaId {}]", aprobada.getId());
                consumoAbsolutoForm.setEstado("FAILED");
                consumoAbsolutoForm.setCause("getClientForConcesionaria");
                consumoAbsolutoForm.setConcesionariaId(aprobada.getId());
                logConsumoAbsolutoForm(consumoAbsolutoForm);
                continue;
            }
            log.info("[ConsumoAbsoluto.ejecutar][SUCCESS getClientForConcesionaria][ConcesionariaId {}]", aprobada.getId());
            // GET ALL ESTADO DE CUENTAS FROM CONCESIONARIA
            List<EstadoCuentasForm> estadoCuentasForms = new ArrayList<>();
            try {
                estadoCuentasForms =
                        estadoCuentasManager.getDao().selectEstadoCuentasByConcesionariaId(aprobada.getId());
                log.info("[EJECUTAR][SUCCESS selectEstadoCuentasByConcesionariaId][ConcesionariaId {}]", aprobada.getId());
            } catch (final SQLException e) {
                e.printStackTrace();
                log.error("[EJECUTAR][FAILED selectEstadoCuentasByConcesionariaId][ConcesionariaId {}]", aprobada.getId());
                consumoAbsolutoForm.setEstado("FAILED");
                consumoAbsolutoForm.setCause("selectEstadoCuentasByConcesionariaId");
                consumoAbsolutoForm.setConcesionariaId(aprobada.getId());
                logConsumoAbsolutoForm(consumoAbsolutoForm);
            }
            // UPDATE ALL ESTADOS DE CUENTAS X CONCESIONARIA APROBADA
            for (final EstadoCuentasForm estadoCuentasForm : estadoCuentasForms) {
                final String rqstId = UUID.randomUUID().toString();
                try {
                    final PlanBean planBean = client.get().consultarPlan(Constants.IDENTIFICADOR, estadoCuentasForm.getNroPlanConcesionaria());
                    // insert(planBean, consumo absoluto plan aprobada id);
                    log.info("[EJECUTAR][SUCCESS consultarPlan][ConcesionariaId {}][PlanId {}][RQST {}]",
                            aprobada.getId(), estadoCuentasForm.getNroPlanConcesionaria(), rqstId);
                    try {
                        updateDb(aprobada.getId(), planBean);
                        log.info("[EJECUTAR][SUCCESS updateDb][ConcesionariaId {}][PlanId {}][RQST {}]",
                                aprobada.getId(), estadoCuentasForm.getNroPlanConcesionaria(), rqstId);
                        consumoAbsolutoForm.setEstado("SUCCESS");
                        consumoAbsolutoForm.setCause("updateDb");
                        consumoAbsolutoForm.setConcesionariaId(aprobada.getId());
                        consumoAbsolutoForm.setEstadoCuentaId(estadoCuentasForm.getId());
                        consumoAbsolutoForm.setPlanId(estadoCuentasForm.getNroPlanConcesionaria());
                        consumoAbsolutoForm.setIdRequestResp(rqstId);
                        logConsumoAbsolutoForm(consumoAbsolutoForm);
                    } catch (final SQLException e) {
                        e.printStackTrace();
                        log.error("[EJECUTAR][FAILED updateDb][ConcesionariaId {}][PlanId {}][RQST {}]",
                                aprobada.getId(), estadoCuentasForm.getNroPlanConcesionaria(), rqstId);
                        consumoAbsolutoForm.setEstado("FAILED");
                        consumoAbsolutoForm.setCause("updateDb");
                        consumoAbsolutoForm.setConcesionariaId(aprobada.getId());
                        consumoAbsolutoForm.setEstadoCuentaId(estadoCuentasForm.getId());
                        consumoAbsolutoForm.setPlanId(estadoCuentasForm.getNroPlanConcesionaria());
                        consumoAbsolutoForm.setIdRequestResp(rqstId);
                        logConsumoAbsolutoForm(consumoAbsolutoForm);
                    }
                } catch (final ClientException e) {
                    e.printStackTrace();
                    log.error("[EJECUTAR][FAILED consultarPlan][ConcesionariaId {}][PlanId {}]",
                            aprobada.getId(), estadoCuentasForm.getNroPlanConcesionaria());
                    consumoAbsolutoForm.setEstado("FAILED");
                    consumoAbsolutoForm.setCause("consultarPlan");
                    consumoAbsolutoForm.setConcesionariaId(aprobada.getId());
                    consumoAbsolutoForm.setEstadoCuentaId(estadoCuentasForm.getId());
                    consumoAbsolutoForm.setPlanId(estadoCuentasForm.getNroPlanConcesionaria());
                    consumoAbsolutoForm.setIdRequestResp(rqstId);
                    logConsumoAbsolutoForm(consumoAbsolutoForm);
                }
            }
            // si todos los estados de cuenta resultaron exitosos => mark [consumo absoluto aprobada] as success => [query]
        }
        // si todas las aprobadas resultaron exitosas => mark [consumo absoluto] as success => [query]
    }


    private Optional<ConcesionariaServiceContract> getClient(final Long concesionariaId) {
        return clientFactoryAdapter.getClientForConcesionaria(concesionariaId, configurarConcesionariaManager);
    }


    /* DESNORMALIZER */
    private void updateDb(final Long concesionariaId, final PlanBean update) throws SQLException {
        final ConsumerForm consumer = new ConsumerForm();
        consumer.setDocumento(update.getClienteDocumento());
        if (!consumerManager.getDao().valid(consumer)) {
            // fail due to invalid plan bean
        }
        updateEstadoCuentaDb(update, concesionariaId);
        updateCuotaDb(update);
    }

    private void updateEstadoCuentaDb(final PlanBean update, final Long concesionariaId) throws SQLException {

        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        estadoCuenta.setNroPlanConcesionaria(update.getPlanId());
        estadoCuenta.setEstado(update.getPlanEstado());
        estadoCuenta.setFechaAltaConcesionaria(update.getPlanFechaAlta());
        update.getPlanFechaUltimaActualizacion();

        estadoCuenta.setDocumentoCliente(update.getClienteDocumento());
        estadoCuenta.setVehiculo(update.getVehiculoId());
        estadoCuenta.setConcesionariaId(concesionariaId);
        estadoCuentasManager.getDao().update(estadoCuenta);
    }

    private void updateCuotaDb(final PlanBean update) throws SQLException {

        for (final CuotaBean cuotaBean : update.getCuotas()) {
            final CuotasForm cuota = new CuotasForm();
            cuota.setEstadoCuentaId(update.getPlanId());
            cuota.setNroCuota(cuotaBean.getCuotaNroCuota());
            cuota.setFechaVencimiento(cuotaBean.getCuotaFechaVencimiento());
            cuota.setMonto(cuotaBean.getCuotaMonto());
            cuota.setFechaPago(cuotaBean.getCuotaFechaPago());
            cuota.setFechaAltaConcesionaria(cuotaBean.getCuotaFechaAlta());
            cuotasManager.getDao().update(cuota);
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