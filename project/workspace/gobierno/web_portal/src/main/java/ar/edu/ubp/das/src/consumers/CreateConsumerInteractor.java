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
import java.util.Arrays;

public class CreateConsumerInteractor implements Interactor<Boolean> {

    private ConsumerManager consumerManager;

    public CreateConsumerInteractor(final MSConsumerDao msConsumerDao) {
        this.consumerManager = new ConsumerManager(msConsumerDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException {

        final Pair<String, Boolean> documento = form.isItemValid("documento");
        final Pair<String, Boolean> nombre = form.isItemValid("nombre");
        final Pair<String, Boolean> apellido = form.isItemValid("apellido");
        final Pair<String, Boolean> nro_telefono = form.isItemValid("nro_telefono");
        final Pair<String, Boolean> email = form.isItemValid("email");

        final Boolean someIsMissing = Arrays.asList(documento, nombre, apellido, nro_telefono, email)
                .stream().anyMatch(v -> v.snd == false);

        if (someIsMissing)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        consumerManager.getDao().insert(form.convertTo(ConsumerForm.class));
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }

}