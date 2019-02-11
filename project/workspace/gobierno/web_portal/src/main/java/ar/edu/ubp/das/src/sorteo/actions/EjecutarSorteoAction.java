package ar.edu.ubp.das.src.sorteo.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.jobs.sorteo.SendEmail;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJob;
import clients.factory.ClientFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EjecutarSorteoAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping, final DynaActionForm form, final HttpServletRequest request,
                                 final HttpServletResponse response) throws RuntimeException {

        final DatasourceConfig datasourceConfig = ModuleConfigImpl.getDatasourceById("default");
        final SorteoJob sorteoJob = new SorteoJob(datasourceConfig, ClientFactory.getInstance(), new SendEmail());
        sorteoJob.execute(null);

        return mapping.getForwardByName(ResponseForward.SUCCESS.getForward());
    }
}