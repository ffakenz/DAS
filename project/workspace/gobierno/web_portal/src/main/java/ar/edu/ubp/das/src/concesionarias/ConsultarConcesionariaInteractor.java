package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.mvc.util.Pair;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class ConsultarConcesionariaInteractor implements Interactor<Optional<ConcesionariaForm>> {

    ConcesionariasManager concesionariasManager;

    public ConsultarConcesionariaInteractor(final DaoImpl concesionariasDao) {
        this.concesionariasManager = new ConcesionariasManager(concesionariasDao);
    }

    @Override
    public InteractorResponse<Optional<ConcesionariaForm>> execute(final DynaActionForm form) throws SQLException {

        final Pair<String, Boolean> id = form.isItemValid("id");

        final Boolean someIsMissing = Arrays.asList(id)
                .stream().anyMatch(v -> v.snd == false);

        if (someIsMissing)
            return new InteractorResponse<>(ResponseForward.WARNING, Optional.empty());

        final Optional<ConcesionariaForm> aprobadas = concesionariasManager.getDao().selectById(Long.valueOf(id.fst));

        return new InteractorResponse(ResponseForward.SUCCESS, aprobadas);
    }
}