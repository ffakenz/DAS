package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ConsultarAprobadasAction implements Action {
    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, RuntimeException {
        final Interactor action = new ConsultarAprobadasInteractor();
        final InteractorResponse result = action.execute(form).apply(DaoFactory::getDao);

        final List<ConcesionariaForm> aprobadas = ((List<ConcesionariaForm>) result.getResult());


        request.setAttribute("aprobadas", aprobadas);

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
