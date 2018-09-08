package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ConsultarAprobadasAction implements Action {
    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, RuntimeException {
        final ConsultarAprobadasInteractor action = new ConsultarAprobadasInteractor();
        final InteractorResponse<List<ConcesionariaForm>> result = action.execute(form);

        final List<ConcesionariaForm> aprobadas = result.getResult();


        request.setAttribute("aprobadas", aprobadas);

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
