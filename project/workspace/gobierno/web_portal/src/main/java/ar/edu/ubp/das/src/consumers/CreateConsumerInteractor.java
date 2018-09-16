package ar.edu.ubp.das.src.consumers;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import com.sun.tools.javac.util.Pair;

import java.sql.SQLException;
import java.util.Arrays;

public class CreateConsumerInteractor implements Interactor<Boolean> {

    private ConsumerManager consumerManager;

    public CreateConsumerInteractor(final MSConsumerDao msConsumerDao) {
        this.consumerManager = new ConsumerManager(msConsumerDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException {
        final Pair<String, Boolean> documento = isItemValid("documento", form);
        final Pair<String, Boolean> concesionaria = isItemValid("concesionaria", form);
        final Pair<String, Boolean> nombre = isItemValid("nombre", form);
        final Pair<String, Boolean> apellido = isItemValid("apellido", form);
        final Pair<String, Boolean> nro_telefono = isItemValid("nro_telefono", form);
        final Pair<String, Boolean> email = isItemValid("email", form);

        final Boolean someIsMissing = Arrays.asList(documento, concesionaria, nombre, apellido, nro_telefono, email)
                .stream().anyMatch(v -> v.snd == false);

        // Some error occur with the input values
        if (someIsMissing)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        consumerManager.getDao().insert(form.convertTo(ConsumerForm.class));
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }

}