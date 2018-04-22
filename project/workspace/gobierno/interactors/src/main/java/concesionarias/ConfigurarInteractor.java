package concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.Aprobar;
import concesionarias.boundaries.Configurar;
import concesionarias.boundaries.ConsultarAprobadas;
import concesionarias.forms.ConcesionariaForm;
import concesionarias.forms.ConfigParamForm;
import core.Interactor;
import core.InteractorResponse;
import core.ResponseForward;

import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConfigurarInteractor implements Interactor, Configurar {

    @Override
    public Function<BiFunction<String, String, Dao>, Boolean> configurarConcesionaria(ConfigParamForm configParam) {
        return daoFactory -> {
            Dao daoConfigurar = daoFactory.apply("ConfigurarConcesionaria", "concesionarias");
            Dao daoConcesionarias = daoFactory.apply("Concesionarias", "concesionarias");

            ConsultarAprobadas consultor = new ConsultarAprobadasInteractor();
            List<ConcesionariaForm> aprobadas = consultor.consultarAprobadas().apply(daoConcesionarias);

            try {
                if(aprobadas.stream().anyMatch( c -> c.getId() == configParam.getConcesionariaId())) {
                    daoConfigurar.insert(configParam);
                    return true;
                } else {
                    return false;
                }
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    @Override
    public Function<BiFunction<String, String, Dao>, InteractorResponse> execute(DynaActionForm form) {
        return daoFactory -> {

            Boolean result = configurarConcesionaria((ConfigParamForm) form).apply(daoFactory);

            InteractorResponse response = new InteractorResponse();
            response.setResult(result);
            if(result){
                response.setResponse(ResponseForward.SUCCESS);
            } else {
                response.setResponse(ResponseForward.FAILURE);
            }

            return response;
        };
    }
}
