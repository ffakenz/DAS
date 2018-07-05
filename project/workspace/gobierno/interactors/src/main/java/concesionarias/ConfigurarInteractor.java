package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import beans.ConcesionariaForm;
import beans.ConfigParamForm;
import concesionarias.boundaries.Configurar;
import concesionarias.boundaries.ConsultarAprobadas;
import core.InteractorResponse;
import core.ResponseForward;

import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConfigurarInteractor implements Configurar {

    @Override
    public Function<BiFunction<String, String, Dao>, Boolean> configurarConcesionaria(final ConfigParamForm configParam) {
        return daoFactory -> {
            final Dao daoConfigurar = daoFactory.apply("ConfigurarConcesionaria", "concesionarias");
            final Dao daoConcesionarias = daoFactory.apply("Concesionarias", "concesionarias");

            final ConsultarAprobadas consultor = new ConsultarAprobadasInteractor();
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
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(final DynaActionForm form) {
        return daoFactory -> {

            final Boolean result = configurarConcesionaria((ConfigParamForm) form).apply(daoFactory);

            final InteractorResponse response = new InteractorResponse();
            response.setResult(result);
            if (result) {
                response.setResponse(ResponseForward.SUCCESS);
            } else {
                response.setResponse(ResponseForward.FAILURE);
            }

            return response;
        };
    }
}
