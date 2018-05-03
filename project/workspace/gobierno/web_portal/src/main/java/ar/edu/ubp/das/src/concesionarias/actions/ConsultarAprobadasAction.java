package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import concesionarias.ConsultarAprobadasInteractor;
import concesionarias.forms.ConcesionariaForm;
import core.Interactor;
import core.InteractorResponse;
import login.LoginInteractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConsultarAprobadasAction implements Action {
    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        Interactor action = new ConsultarAprobadasInteractor();
        InteractorResponse result = action.execute(form).apply(DaoFactory::getDao);

        List<ConcesionariaForm> aprobadas = ((List<ConcesionariaForm>)result.getResult());


        request.setAttribute("aprobadas", aprobadas);

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
