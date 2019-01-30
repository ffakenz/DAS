package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultarConcesionariaConfigParamInteractor implements Interactor<List<ConfigurarConcesionariaForm>> {

    ConfigurarConcesionariaManager configurarConcesionariaManager;

    public ConsultarConcesionariaConfigParamInteractor(final DaoImpl msConfigurarConcesionariaDao) {

        this.configurarConcesionariaManager = new ConfigurarConcesionariaManager(msConfigurarConcesionariaDao);
    }

    @Override
    public InteractorResponse<List<ConfigurarConcesionariaForm>> execute(final DynaActionForm form) {

        final Pair<String, Boolean> id = form.isItemValid("id");

        final Boolean someIsMissing = Arrays.asList(id)
                .stream().anyMatch(v -> v.snd == false);

        if (someIsMissing)
            return new InteractorResponse<>(ResponseForward.WARNING, new ArrayList<>());

        List<ConfigurarConcesionariaForm> configurarConcesionariaForms = null;

        try {
            configurarConcesionariaForms = this.configurarConcesionariaManager.getDao().selectParamsByConcesionariaId(Long.valueOf(id.fst));

            return new InteractorResponse(ResponseForward.SUCCESS, configurarConcesionariaForms);

        } catch (final SQLException e) {
            e.printStackTrace();
            return new InteractorResponse(ResponseForward.FAILURE, new ArrayList<>());
        }
    }
}
