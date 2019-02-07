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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

public class CalendarCellModalAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final String sorteoId = request.getParameter("cell_id");

        if (sorteoId != null) {
            final Long idSorteo = Long.valueOf(sorteoId);

            final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
            final SorteoJobManager sorteoJobManager = new SorteoJobManager(datasourceConfig);
            try {
                final Optional<SorteoForm> sorteo = sorteoJobManager.getMsSorteoDao().getSorteoById(idSorteo);
                if (sorteo.isPresent()) {
                    request.setAttribute("sorteo", sorteo.get());
                    log.info("CalendarCellModalAction [SUCCEDED] [REASON - {}]", sorteo.toString());
                    return mapping.getForwardByName("update"); // sorteo cell
                } else {
                    log.info("CalendarCellModalAction [FAILED] [REASON - {}][SorteoId = {}]", "Sorteo by Id was empty", idSorteo);
                    return mapping.getForwardByName(ResponseForward.FAILURE.getForward()); // error: sorteo != cell_id
                }
            } catch (final SQLException e) {
                e.printStackTrace();
                log.info("CalendarCellModalAction [FAILED] [REASON - {}][SorteoId = {}]", e.getMessage(), idSorteo);
                return mapping.getForwardByName(ResponseForward.FAILURE.getForward()); // error: DB
            }
        }

        final String cellDay = request.getParameter("cell_day");
        final String cellMonth = request.getParameter("cell_month");
        final String cellYear = request.getParameter("cell_year");
        request.setAttribute("cell_day", cellDay);
        request.setAttribute("cell_month", cellMonth);
        request.setAttribute("cell_year", cellYear);
        return mapping.getForwardByName("create"); // empty cell

    }

}