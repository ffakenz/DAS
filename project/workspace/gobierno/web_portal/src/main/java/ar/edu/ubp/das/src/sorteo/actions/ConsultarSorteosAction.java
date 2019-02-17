package ar.edu.ubp.das.src.sorteo.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJobManager;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ConsultarSorteosAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        final SorteoJobManager sorteoJobManager = new SorteoJobManager(datasourceConfig);

        try {
            final List<SorteoForm> sorteos = sorteoJobManager.getMsSorteoDao().select();
            request.setAttribute(Constants.SORTEOS_LIST_RQST_ATTRIBUTE, sorteos);
            log.info("ConsultarSorteosAction [SUCCEDED] [REASON - {}]", sorteos);
            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("ConsultarSorteosAction [FAILED] [REASON - {}]", e.getMessage());
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }
}