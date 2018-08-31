package ar.edu.ubp.das.src.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ConfigurarInteractor {

    public Boolean configurarConcesionaria(final ConfigParamForm configParam) {

        final Dao daoConfigurar = new MSConfigurarConcesionariaDao();
        final Dao daoConcesionarias = new MSConcesionariasDao();

        final ConsultarAprobadasInteractor consultor = new ConsultarAprobadasInteractor();
        final List<ConcesionariaForm> aprobadas = consultor.consultarAprobadas().apply(daoConcesionarias);

        try {
            if (aprobadas.stream().anyMatch(c -> c.getId() == configParam.getConcesionariaId())) {
                daoConfigurar.insert(configParam);
                return true;
            } else {
                return false;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public InteractorResponse execute(final DynaActionForm form) {

        final Boolean result = configurarConcesionaria((ConfigParamForm) form);

        final InteractorResponse response = new InteractorResponse();
        response.setResult(Optional.of(result));
        if (result) {
            response.setResponse(ResponseForward.SUCCESS);
        } else {
            response.setResponse(ResponseForward.FAILURE);
        }

        return response;
    }
}
