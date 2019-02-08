package ar.edu.ubp.das.src.sorteo.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJobManager;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSSorteoDao;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

// Updates estado or fecha_ejecucion
public class ActualizarSorteoAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {


        final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        final SorteoJobManager sorteoJobManager = new SorteoJobManager(datasourceConfig);
        try {
            final MSSorteoDao msSorteoDao = sorteoJobManager.getMsSorteoDao();
            final Pair<String, Boolean> id = form.isItemValid("id");
            final Pair<String, Boolean> estado = form.isItemValid("estado");
            final Pair<String, Boolean> fechaEjecucion = form.isItemValid("fecha_ejecucion");
            if (estado.snd && id.snd) {
                msSorteoDao.update(form.convertTo(SorteoForm.class));
                log.info("ActualizarSorteoAction [SUCCEEDED] [REASON - {}]", estado.fst);
                request.setAttribute(Constants.RESULT_RQST_ATTRIBUTE, "estado");
                return jsonResult("{\"result\": \"OK\"}");
            } else if (fechaEjecucion.snd && id.snd) {
                msSorteoDao.actualizarFechaSorteo(form.convertTo(SorteoForm.class));
                log.info("ActualizarSorteoAction [SUCCEEDED] [REASON - {}]", fechaEjecucion.fst);
                request.setAttribute(Constants.RESULT_RQST_ATTRIBUTE, "fecha");
                return jsonResult("{\"result\": \"OK\"}");
            } else {
                log.info("ActualizarSorteoAction [FAILED] [REASON - {}]", "form is invalid");
                return jsonResult("{\"result\": \"WARNING\"}");
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("ActualizarSorteoAction [FAILED] [REASON - {}]", e.getMessage());
            return jsonResult("{\"result\": \"FAILURE\"}");
        }
    }
}