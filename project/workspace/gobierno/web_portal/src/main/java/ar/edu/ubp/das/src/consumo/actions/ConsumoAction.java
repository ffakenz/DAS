package ar.edu.ubp.das.src.consumo.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.consumo.ConsumoJob;
import ar.edu.ubp.das.src.jobs.consumo.ConsumoJobManager;
import ar.edu.ubp.das.src.jobs.consumo.forms.JobConsumoForm;
import clients.factory.ClientFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConsumoAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        ConsumoJob consumoJob = new ConsumoJob(datasourceConfig, ClientFactory.getInstance());
        consumoJob.execute(null);
        ConsumoJobManager consumoJobManager = new ConsumoJobManager(datasourceConfig);
        try {
            JobConsumoForm lastJob = consumoJobManager.getMsJobConsumoDao().getLastJob();
            log.info("JOB {} EXECUTED SUCCESSFULLY ", lastJob.getId());

            request.setAttribute("message", String.format("JOB %s EXECUTED SUCCESSFULLY", lastJob.getId()));

            return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
        } catch (SQLException e) {
            e.printStackTrace();
            return mapping.getForwardByName(ResponseForward.FAILURE.getForward());
        }
    }
}