package ar.edu.ubp.das.src.usuarios.actions;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.DaoFactory;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.consumers.UpdateConsumerInteractor;
import ar.edu.ubp.das.src.core.InteractorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.CONSUMERS_DAO_PACKAGE;
import static ar.edu.ubp.das.src.utils.Constants.CONSUMER_DAO_NAME;

public class ActualizarConsumerAction implements Action {

    @Override
    public ForwardConfig execute(final ActionMapping mapping,
                                 final DynaActionForm form,
                                 final HttpServletRequest request,
                                 final HttpServletResponse response)

            throws SQLException, RuntimeException {


        final DaoImpl msConsumersDao = DaoFactory.getDao(CONSUMER_DAO_NAME, CONSUMERS_DAO_PACKAGE);

        final UpdateConsumerInteractor updateConsumerInteractor = new UpdateConsumerInteractor(msConsumersDao);

        final InteractorResponse<Boolean> resp = updateConsumerInteractor.execute(form);

        return mapping.getForwardByName(resp.getResponse().getForward());
    }
}
