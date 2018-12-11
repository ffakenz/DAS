package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.ConsultarConcesionariaConfigParamInteractor;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static ar.edu.ubp.das.src.utils.Constants.CONCESIONARIAS_DAO_PACKAGE;
import static ar.edu.ubp.das.src.utils.Constants.CONFIG_CONCESIONARIAS_DAO_NAME;

public class ConsultarConcesionariaConfigParamAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) {

        final DaoImpl msConfigurarConcesionariaDao = DaoFactory.getDao(CONFIG_CONCESIONARIAS_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);

        final ConsultarConcesionariaConfigParamInteractor interactor =
                new ConsultarConcesionariaConfigParamInteractor(msConfigurarConcesionariaDao);

        final InteractorResponse<List<ConfigurarConcesionariaForm>> resp = interactor.execute(form);

        if (!resp.getResponse().equals(ResponseForward.SUCCESS))
            return mapping.getForwardByName(resp.getResponse().getForward());

        return jsonResult(resp.getResult());
    }
}
