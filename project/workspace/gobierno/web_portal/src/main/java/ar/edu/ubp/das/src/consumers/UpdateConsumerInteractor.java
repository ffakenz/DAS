package ar.edu.ubp.das.src.consumers;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;

public class UpdateConsumerInteractor implements Interactor<Boolean> {

    private ConsumerManager consumerManager;

    public UpdateConsumerInteractor(final MSConsumerDao msConsumerDao) {
        this.consumerManager = new ConsumerManager(msConsumerDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException {
        final Pair<String, Boolean> documento = form.isItemValid("documento");
        final Pair<String, Boolean> concesionaria = form.isItemValid("concesionaria");
        final Pair<String, Boolean> nro_telefono = form.isItemValid("nro_telefono");
        final Pair<String, Boolean> email = form.isItemValid("email");

        final Boolean requeridosIsInvalid = !documento.snd || !concesionaria.snd; // no puede faltar ni el documento ni la concesionaria
        final Boolean optionalsIsInvalid = !email.snd && !nro_telefono.snd; // no pueden faltar email y telefono al mismo tiempo

        // Some error occur with the input values
        if (requeridosIsInvalid || optionalsIsInvalid)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        consumerManager.getDao().update(form.convertTo(ConsumerForm.class));
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}


