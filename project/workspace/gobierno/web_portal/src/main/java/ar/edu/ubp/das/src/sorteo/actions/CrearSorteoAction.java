package ar.edu.ubp.das.src.sorteo.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJobManager;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

// TODO: check if this action should validate if sorteo can be created
public class CrearSorteoAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        final SorteoJobManager sorteoJobManager = new SorteoJobManager(datasourceConfig);
        try {
            final Pair<String, Boolean> fechaEjecucion = form.isItemValid("fecha_ejecucion");
            final SorteoForm sorteoForm = form.convertTo(SorteoForm.class);
            if (!fechaEjecucion.snd) {
                sorteoForm.setFechaEjecucion(DateUtils.getDayDate());
            }
            sorteoJobManager.getMsSorteoDao().insert(sorteoForm);
            log.info("CrearSorteoAction [SUCCEDED][Sorteo {}]", sorteoForm);
            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("CrearSorteoAction [FAILED] [REASON - {}]", e.getMessage());
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }
}