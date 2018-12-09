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

        final DaoImpl msConcesionariasDao = DaoFactory.getDao(DAO_CONCESIONARIA, DAO_CONCESIONARIA_PCKG);
        final DaoImpl msConfigurarConcesionariaDao = DaoFactory.getDao(DAO_CONFIG_CONCESIONARIA, DAO_CONCESIONARIA_PCKG);
        final DaoImpl msConfigTecnoParamDao = DaoFactory.getDao(DAO_CONFIG_TECNO_PARAM, DAO_CONCESIONARIA_PCKG);

        final ConfigurarConcesionariaInteractor action = new ConfigurarConcesionariaInteractor(msConfigurarConcesionariaDao, msConcesionariasDao, msConfigTecnoParamDao);

        final InteractorResponse<Boolean> result = action.execute(form);

        return mapping.getForwardByName(result.getResponse().getForward());
    }
}
