package ar.edu.ubp.das.src.queries.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.consumo.Desnormalizer;
import beans.NotificationUpdate;
import beans.PlanBean;
import clients.ConcesionariaServiceContract;
import clients.factory.ClientFactory;
import clients.responses.ClientException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class UpdateEstadoCuentaDashAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {


        final Pair<String, Boolean> idConcesionaria = form.isItemValid("id_concesionaria");
        final Pair<String, Boolean> documento = form.isItemValid("documento");
        final Pair<String, Boolean> nroPlanConcesionaria = form.isItemValid("nro_plan_concesionaria");
        if (!idConcesionaria.snd || !documento.snd || !nroPlanConcesionaria.snd) {
            log.error("[UpdateEstadoCuentaDashAction][WARNING form]");
            return mapping.getForwardByName(ResponseForward.WARNING.getForward()); // Some error occur with username / password
        }

        final ClientFactoryAdapter clientFactoryAdapter = new ClientFactoryAdapter(ClientFactory.getInstance());
        final DaoImpl configDao =
                DaoFactory.getDao(CONFIG_CONCESIONARIAS_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);
        final ConfigurarConcesionariaManager configManager =
                new ConfigurarConcesionariaManager(configDao);
        final Optional<ConcesionariaServiceContract> clientForConcesionaria =
                clientFactoryAdapter.getClientForConcesionaria(Long.valueOf(idConcesionaria.fst), configManager);

        if (!clientForConcesionaria.isPresent()) {
            log.error("[UpdateEstadoCuentaDashAction][FAILED getClientForConcesionaria]");
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }

        try {
            final PlanBean planBean =
                    clientForConcesionaria.get().consultarPlan(IDENTIFICADOR, Long.valueOf(nroPlanConcesionaria.fst));
            log.info("[UpdateEstadoCuentaDashAction][SUCCESS consultarPlan] {}", planBean);

            final List<NotificationUpdate> notificationUpdates = planBean.toNotificationUpdates();
            final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
            final Desnormalizer desnormalizer = new Desnormalizer(datasourceConfig);
            for (final NotificationUpdate notificationUpdate : notificationUpdates) {
                try {
                    desnormalizer.updateDb(Long.valueOf(idConcesionaria.fst), notificationUpdate);
                } catch (final SQLException e) {
                    e.printStackTrace();
                    log.error("[UpdateEstadoCuentaDashAction][FAILED Desnormalizer][Update {}]", notificationUpdate);
                }
            }
            log.info("[UpdateEstadoCuentaDashAction][SUCCESS Desnormalizer] {}", planBean);
            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (final ClientException e) {
            e.printStackTrace();
            log.error("[UpdateEstadoCuentaDashAction][FAILED consultarPlan]");
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }
}
