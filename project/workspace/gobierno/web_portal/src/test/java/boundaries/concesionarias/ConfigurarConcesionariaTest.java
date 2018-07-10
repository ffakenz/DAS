package boundaries.concesionarias;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.ConfigurarInteractor;
import ar.edu.ubp.das.src.concesionarias.boundaries.Configurar;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;
import mocks.MSConcesionariasDaoMock;
import mocks.MSConfigurarConcesionariaDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.function.BiFunction;

public class ConfigurarConcesionariaTest {

    Dao confDao = new MSConfigurarConcesionariaDaoMock();
    Dao concDao = new MSConcesionariasDaoMock();
    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if (daoName.equals("Concesionarias") && daoPackage.equals("concesionarias")) {
            return concDao;
        } else if (daoName.equals("ConfigurarConcesionaria") && daoPackage.equals("concesionarias")) {
            return confDao;
        } else return null;
    };


    @Test
    public void validarConfigurarConcesionaria() {
        // Dado un configParamas
        final ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(Long.valueOf(1));
        configParam.setConfigTecno("REST");
        configParam.setConfigParam("url");
        configParam.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        final Configurar configurador = new ConfigurarInteractor();

        final Boolean result = configurador.configurarConcesionaria(configParam).apply(daoFactoryMock);

        assert (result);

        try {
            assert (confDao.select(null).contains(configParam));
        } catch (final SQLException ex) {
            ex.printStackTrace();
            assert (false);
        }
    }
}
