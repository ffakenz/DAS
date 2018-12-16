package ar.edu.ubp.das.src.concesionarias.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.src.concesionarias.TestConfigInteractor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class TestConfigAction implements Action {


    private static final Logger log = LoggerFactory.getLogger(TestConfigAction.class);

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, RuntimeException {


        log.info(form.toString());

        final TestConfigInteractor testConfigInteractor = new TestConfigInteractor();
        final InteractorResponse<String> resp = testConfigInteractor.execute(form);

        return mapping.getForwardByName(resp.getResponse().getForward());
    }
}