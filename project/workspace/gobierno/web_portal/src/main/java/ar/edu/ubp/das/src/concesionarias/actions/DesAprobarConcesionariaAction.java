package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.ConsultarConcesionariaInteractor;
import ar.edu.ubp.das.src.concesionarias.DesAprobarInteractor;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

import static ar.edu.ubp.das.src.utils.Constants.*;

public class DesAprobarConcesionariaAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping,
                                 final DynaActionForm form,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)

            throws SQLException, RuntimeException {

        final DaoImpl msConcesionariaDao = DaoFactory.getDao(CONCESIONARIAS_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);

        final DesAprobarInteractor action = new DesAprobarInteractor(msConcesionariaDao);
        final InteractorResponse<Boolean> result = action.execute(form);
        request.setAttribute(RESULT_RQST_ATTRIBUTE, result.getResponse());

        if (result.getResult()) {
            final ConsultarConcesionariaInteractor consultarConcesionariaInteractor = new ConsultarConcesionariaInteractor(msConcesionariaDao);
            final InteractorResponse<Optional<ConcesionariaForm>> aprobada = consultarConcesionariaInteractor.execute(form);

            request.setAttribute(CONCESIONARIA_RQST, aprobada.getResult().get());
        }

        logAction(mapping, form, request, response);
        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
