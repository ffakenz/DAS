package ar.edu.ubp.das.src.jobs.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.CONSUMO_DAO_PACKAGE;
import static ar.edu.ubp.das.src.utils.Constants.VIEW_CONSUMO_RESULTS__DAO_NAME;

public class ViewConsumoJobResultsAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final DaoImpl viewConsumoResults =
                DaoFactory.getDao(VIEW_CONSUMO_RESULTS__DAO_NAME, CONSUMO_DAO_PACKAGE);
        try {
            final List<SorteoForm> sorteos = viewConsumoResults.select();
            request.setAttribute(Constants.JOB_RESULTS_REPORT_RQST_ATTRIBUTE, sorteos);
            log.info("ViewConsumoJobResultsAction [SUCCEDED] [REASON - {}]", sorteos);
            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("ViewConsumoJobResultsAction [FAILED] [REASON - {}]", e.getMessage());
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }
}