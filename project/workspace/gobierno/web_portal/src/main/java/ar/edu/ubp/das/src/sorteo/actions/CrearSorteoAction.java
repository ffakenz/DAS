package ar.edu.ubp.das.src.sorteo.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJobManager;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

// TODO: check if this action should validate if sorteo can be created
public class CrearSorteoAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        final SorteoJobManager sorteoJobManager = new SorteoJobManager(datasourceConfig);

        final Pair<String, Boolean> cellDay = form.isItemValid("cell_day");
        final Pair<String, Boolean> cellMonth = form.isItemValid("cell_month");
        final Pair<String, Boolean> cellYear = form.isItemValid("cell_year");

        if (!(cellDay.snd && cellMonth.snd && cellYear.snd)) {
            log.info("CrearSorteoAction [FAILED][REASON - {}]", "Wrong Parameters");
            return jsonResult("{\"result\": \"FAILURE\"}");
        }
        final String day = cellDay.fst;
        final String month = cellMonth.fst;
        final String year = cellYear.fst;
        final SorteoForm sorteoForm = new SorteoForm();
        final Date hoy = DateUtils.getDayDate();
        final Date rqst =
                Date.valueOf(String.format("%s-%s-%s", year, month, day));
        if (rqst.before(hoy)) {
            log.info("CrearSorteoAction [FAILED][REASON - {}]", "Cannot Create Sorteo with previous date than today");
            return jsonResult("{\"result\": \"FAILURE\"}");
        }
        sorteoForm.setFechaEjecucion(rqst);
        try {
            sorteoJobManager.getMsSorteoDao().insert(sorteoForm);
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("CrearSorteoAction [FAILED] [REASON - {}]", e.getMessage());
            return jsonResult("{\"result\": \"FAILURE\"}");
        }
        log.info("CrearSorteoAction [SUCCEDED][Sorteo {}]", sorteoForm);
        return jsonResult("{\"result\": \"OK\"}");
    }

    public static void main(final String[] args) {
        System.out.println(String.format("lol %s", "me"));
    }
}
