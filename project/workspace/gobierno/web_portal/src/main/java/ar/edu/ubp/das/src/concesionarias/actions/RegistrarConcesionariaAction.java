package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.RegistrarConcesionariaInteractor;
import ar.edu.ubp.das.src.core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.CONCESIONARIAS_DAO_NAME;
import static ar.edu.ubp.das.src.utils.Constants.CONCESIONARIAS_DAO_PACKAGE;

public class RegistrarConcesionariaAction implements Action {
    @Override
    public ForwardConfig execute(final ActionMapping mapping,
                                 final DynaActionForm form,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)

            throws SQLException, RuntimeException {


        final DaoImpl msConcesionariaDao = DaoFactory.getDao(CONCESIONARIAS_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);

        final RegistrarConcesionariaInteractor action = new RegistrarConcesionariaInteractor(msConcesionariaDao);

        final InteractorResponse<Boolean> result = action.execute(form);

        logAction(mapping, form, request, response);
        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
