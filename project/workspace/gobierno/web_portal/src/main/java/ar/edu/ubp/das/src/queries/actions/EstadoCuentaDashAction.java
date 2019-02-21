package ar.edu.ubp.das.src.queries.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.queries.forms.EstadoCuentaDashForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class EstadoCuentaDashAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final DaoImpl viewEstadoCuentaDashDao =
                DaoFactory.getDao(ESTADO_CUENTA_DASH_DAO_NAME, QUERIES_DAO_PACKAGE);
        try {
            final List<EstadoCuentaDashForm> dash = viewEstadoCuentaDashDao.select();
            request.setAttribute(QUERY_RESULT_RQST_ATTRIBUTE, dash);
            log.info("EstadoCuentaDashAction [SUCCEDED] [REASON - {}]", dash);
            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (final SQLException e) {
            e.printStackTrace();
            log.info("EstadoCuentaDashAction [FAILED] [REASON - {}]", e.getMessage());
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }
}