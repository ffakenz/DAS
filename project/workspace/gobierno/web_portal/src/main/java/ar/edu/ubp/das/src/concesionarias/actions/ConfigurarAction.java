package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.ConfigurarConcesionariaInteractor;
import ar.edu.ubp.das.src.core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConfigurarAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, RuntimeException {

        final DaoImpl msConfigurarConcesionariaDao = DaoFactory.getDao("ConfigurarConcesionaria", "concesionarias");
        final DaoImpl msConcesionariasDao = DaoFactory.getDao("Concesionarias", "concesionarias");

        final ConfigurarConcesionariaInteractor action = new ConfigurarConcesionariaInteractor(msConfigurarConcesionariaDao, msConcesionariasDao);
        final InteractorResponse<Boolean> result = action.execute(form);

        final Boolean configurationSucceeded = result.getResult();

        request.setAttribute("configurationSucceeded", configurationSucceeded);

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
