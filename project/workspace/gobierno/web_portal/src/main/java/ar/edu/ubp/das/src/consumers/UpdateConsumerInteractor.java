package ar.edu.ubp.das.src.consumers;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UpdateConsumerInteractor implements Interactor<Boolean> {

    private ConsumerManager consumerManager;

    public UpdateConsumerInteractor(final MSConsumerDao msConsumerDao) {
        this.consumerManager = new ConsumerManager(msConsumerDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(DynaActionForm form) throws SQLException {
        final String NOT_FOUND = "NOT_FOUND";
        final String documento = form.getItem("documento").orElse(NOT_FOUND);
        final String concesionaria = form.getItem("concesionaria").orElse(NOT_FOUND);
        final String nro_telefono= form.getItem("nro_telefono").orElse(NOT_FOUND);
        final String email = form.getItem("email").orElse(NOT_FOUND);

        Boolean requeridosIsInvalid = documento.equals(NOT_FOUND) || concesionaria.equals(NOT_FOUND);
        Boolean optionalsIsInvalid = email.equals(NOT_FOUND) && nro_telefono.equals(NOT_FOUND);

        // Some error occur with the input values
        if (requeridosIsInvalid || optionalsIsInvalid)
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        final ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(Long.valueOf(documento));
        consumerForm.setConcesionaria(Long.valueOf(concesionaria));
        consumerForm.setNroTelefono(nro_telefono);
        consumerForm.setEmail(email);

        consumerManager.getDao().update(consumerForm);
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }
}
