package interactors.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.ConfigurarInteractor;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;

public class ConfigurarInteractorTest {

    Dao confDao = new MSConfigurarConcesionariaDao();
    Dao concDao = new MSConcesionariasDao();
    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if (daoName.equals("Concesionarias") && daoPackage.equals("concesionarias")) {
            return concDao;
        } else if (daoName.equals("ConfigurarConcesionaria") && daoPackage.equals("concesionarias")) {
            return confDao;
        } else return null;
    };

    @Test
    public void validarConfiguracionConcecionaria() {
        final Interactor consultor = new ConsultarAprobadasInteractor();
        final Interactor configurador = new ConfigurarInteractor();

        // Dado un configParamas
        final ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(Long.valueOf(1));
        configParam.setConfigTecno("REST");
        configParam.setConfigParam("url");
        configParam.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        // valid que los parametros correspondan a una concesionaria aprobada
        final List<ConcesionariaForm> aprobadas =
                (List<ConcesionariaForm>) consultor.execute(null).apply(daoFactoryMock).getResult();
        // la consecionaria esta aprobada
        assert (aprobadas.stream().anyMatch(c -> c.getId() == configParam.getConcesionariaId()));

        // Ejecuto el configurador
        final InteractorResponse response = configurador.execute(configParam).apply(daoFactoryMock);

        // Valido que la concesionaria se haya configurado exitosamente
        assert (response.getResponse() == ResponseForward.SUCCESS);
        assert (((Boolean) response.getResult()));

        // Valido que se haya insertado exitosamente
        try {
            final List<DynaActionForm> configParams = confDao.select(null);
            assert (configParams.contains(configParam));
        } catch (final SQLException e) {
            e.printStackTrace();
            assert (false);
        }


    }
}
