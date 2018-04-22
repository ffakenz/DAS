package boundaries.concesionarias;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.ConfigurarInteractor;
import concesionarias.boundaries.Configurar;
import concesionarias.forms.ConfigParamForm;
import core.Interactor;
import mocks.MSConcesionariaDaoMock;
import mocks.MSConfigurarConcesionariaDaoMock;
import org.junit.Test;

import java.sql.SQLException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConfigurarConcesionariaTest {

    Dao confDao = new MSConfigurarConcesionariaDaoMock();
    Dao concDao = new MSConcesionariaDaoMock();
    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if(daoName.equals("Concesionarias") && daoPackage.equals("concesionarias")) {
            return concDao;
        } else if(daoName.equals("ConfigurarConcesionaria") && daoPackage.equals("concesionarias")) {
            return confDao;
        }
        else return null;
    };


    @Test
    public void validarConfigurarConcesionaria() {
        // Dado un configParamas
        ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(Long.valueOf(1));
        configParam.setConfigTecno("REST");
        configParam.setConfigParam("url");
        configParam.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        Configurar configurador = new ConfigurarInteractor();

        Boolean result = configurador.configurarConcesionaria(configParam).apply(daoFactoryMock);

        assert(result);

        try {
            assert( confDao.select(null).contains(configParam));
        } catch(SQLException ex) {
            ex.printStackTrace();
            assert(false);
        }
    }
}
