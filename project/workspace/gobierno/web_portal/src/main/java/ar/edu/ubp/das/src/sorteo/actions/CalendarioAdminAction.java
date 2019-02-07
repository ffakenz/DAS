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

public class CalendarioAdminAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final String year = request.getParameter("year");
        final String month = request.getParameter("month");
        request.setAttribute("year", year);
        request.setAttribute("month", month);

        final Integer offset = Integer.valueOf(month) + 1;

        final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        final SorteoJobManager sorteoJobManager = new SorteoJobManager(datasourceConfig);
        try {
            final List<SorteoForm> sorteosByMes = sorteoJobManager.getMsSorteoDao().getSorteosByMes(offset);
            request.setAttribute(Constants.SORTEOS_LIST_RQST_ATTRIBUTE, sorteosByMes);
            log.info("CalendarioAdminAction [SUCCEDED] [REASON - {}]", sorteosByMes);
            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("CalendarioAdminAction [FAILED] [REASON - {}]", e.getMessage());
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }

}