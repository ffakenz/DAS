package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import concesionarias.AprobarInteractor;
import concesionarias.RegistrarInteractor;
import core.Interactor;
import core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

public class AprobarConcesionariaAction implements Action {
    @Override
    public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, RuntimeException {
        Interactor action = new AprobarInteractor();
        InteractorResponse result = action.execute(form).apply(DaoFactory::getDao);

        Optional<String> codigo = ((Optional<String>)result.getResult());


        request.setAttribute("codigo", codigo);

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
