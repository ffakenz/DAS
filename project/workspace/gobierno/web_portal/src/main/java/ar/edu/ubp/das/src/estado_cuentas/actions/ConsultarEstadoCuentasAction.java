package ar.edu.ubp.das.src.estado_cuentas.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.estado_cuentas.ConsultarAllEstadoCuentasInteractor;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class ConsultarEstadoCuentasAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, RuntimeException {

        final DaoImpl msEstadoCuentasDao = DaoFactory.getDao(ESTADO_CUENTAS_DAO_NAME, ESTAD_CUENTAS_DAO_PACKAGE);

        final ConsultarAllEstadoCuentasInteractor action = new ConsultarAllEstadoCuentasInteractor(msEstadoCuentasDao);
        final InteractorResponse<List<EstadoCuentasForm>> result = action.execute(form);

        final List<EstadoCuentasForm> estadoCuentasForms = result.getResult();

        request.setAttribute(ESTADO_CUENTAS_LIST_RQST_ATTRIBUTE, estadoCuentasForms);

        logAction(mapping, form, request, response);
        return mapping.getForwardByName(result.getResponse().getForward());

    }
}
