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

import static ar.edu.ubp.das.src.utils.Constants.*;

public class ConfigurarAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, RuntimeException {

        final DaoImpl msConcesionariasDao = DaoFactory.getDao(CONCESIONARIAS_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);
        final DaoImpl msConfigurarConcesionariaDao = DaoFactory.getDao(CONFIG_CONCESIONARIAS_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);
        final DaoImpl msConfigTecnoParamDao = DaoFactory.getDao(CONFIG_TECNO_PARAM_DAO_NAME, CONCESIONARIAS_DAO_PACKAGE);

        final ConfigurarConcesionariaInteractor action = new ConfigurarConcesionariaInteractor(msConfigurarConcesionariaDao, msConcesionariasDao, msConfigTecnoParamDao);

        final InteractorResponse<Boolean> resp = action.execute(form);

        if (!resp.getResult())
            return mapping.getForwardByName(resp.getResponse().getForward());

        logAction(mapping, form, request, response);

        return jsonResult("{\"result\": \"OK\"}");
    }
}
