package interactors.concesionarias;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.ConfigurarInteractor;
import ar.edu.ubp.das.src.concesionarias.ConsultarAprobadasInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;
import ar.edu.ubp.das.src.core.InteractorResponse;
import ar.edu.ubp.das.src.core.ResponseForward;
import org.junit.Ignore;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ConfigurarInteractorTest {

    Dao confDao = new MSConfigurarConcesionariaDao();

    @Ignore
    public void validarConfiguracionConcecionaria() {
        final ConsultarAprobadasInteractor consultor = new ConsultarAprobadasInteractor();
        final ConfigurarInteractor configurador = new ConfigurarInteractor();

        // Dado un configParamas
        final ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(1L);
        configParam.setConfigTecno("REST");
        configParam.setConfigParam("url");
        configParam.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        // valid que los parametros correspondan a una concesionaria aprobada
        final Optional<List<ConcesionariaForm>> aprobadas =
                consultor.execute(null).getResult();
        // la consecionaria esta aprobada
        assertTrue(aprobadas.get().stream().anyMatch(c -> c.getId() == configParam.getConcesionariaId()));

        // Ejecuto el configurador
        final InteractorResponse<Boolean> response = configurador.execute(configParam);

        // Valido que la concesionaria se haya configurado exitosamente
        assertEquals(ResponseForward.SUCCESS, response.getResponse());
        assertTrue(response.getResult().get());

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
