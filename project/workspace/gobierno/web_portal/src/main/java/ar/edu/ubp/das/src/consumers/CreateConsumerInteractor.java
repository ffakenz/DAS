package ar.edu.ubp.das.src.consumers;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.model.ConsumerManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import ar.edu.ubp.das.src.usuarios.model.UsuarioRol;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CreateConsumerInteractor implements Interactor<Boolean> {

    private ConsumerManager consumerManager;

    public CreateConsumerInteractor(final MSConsumerDao msConsumerDao) {
        this.consumerManager = new ConsumerManager(msConsumerDao);
    }

    @Override
    public InteractorResponse<Boolean> execute(final DynaActionForm form) throws SQLException {
        final String NOT_FOUND = "NOT_FOUND";
        final String documento = form.getItem("documento").orElse(NOT_FOUND);
        final String concesionaria = form.getItem("concesionaria").orElse(NOT_FOUND);
        final String nombre = form.getItem("nombre").orElse(NOT_FOUND);
        final String apellido = form.getItem("apellido").orElse(NOT_FOUND);
        final String nro_telefono= form.getItem("nro_telefono").orElse(NOT_FOUND);
        final String email = form.getItem("email").orElse(NOT_FOUND);

        List<String> values = Arrays.asList(documento, concesionaria, nombre, apellido, nro_telefono, email);

        // Some error occur with the input values
        if (values.stream().anyMatch(v -> v.equals(NOT_FOUND)))
            return new InteractorResponse<>(ResponseForward.WARNING, false);

        final ConsumerForm consumerForm = new ConsumerForm();
        consumerForm.setDocumento(Long.valueOf(documento));
        consumerForm.setConcesionaria(Long.valueOf(concesionaria));
        consumerForm.setNombre(nombre);
        consumerForm.setApellido(apellido);
        consumerForm.setNroTelefono(nro_telefono);
        consumerForm.setEmail(email);

        consumerManager.getDao().insert(consumerForm);
        return new InteractorResponse<>(ResponseForward.SUCCESS, true);
    }

}