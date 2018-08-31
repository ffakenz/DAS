package boundaries.concesionarias;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.ConfigurarInteractor;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigParamForm;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.function.BiFunction;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigurarConcesionariaTest {


    Dao confDao = new MSConfigurarConcesionariaDao();
    Dao concDao = new MSConcesionariasDao();

    ConfigurarInteractor daoFactory;
    BiFunction<String, String, Dao> daoFactoryMock = (daoName, daoPackage) -> {
        if (daoName.equals("Concesionarias") && daoPackage.equals("concesionarias")) {
            return concDao;
        } else if (daoName.equals("ConfigurarConcesionaria") && daoPackage.equals("concesionarias")) {
            return confDao;
        } else return null;
    };

    @Ignore
    public void test01ValidarConfigurarConcesionaria() {
        // Dado un configParamas
        final ConfigParamForm configParam = new ConfigParamForm();
        configParam.setConcesionariaId(Long.valueOf(1));
        configParam.setConfigTecno("REST");
        configParam.setConfigParam("url");
        configParam.setValue("http://localhost:8002/concesionarias_rest_one/concesionariaRestOne");

        final ConfigurarInteractor configurador = new ConfigurarInteractor();

        final Boolean result = configurador.configurarConcesionaria(configParam);

        assert (result);

        try {
            assert (confDao.select(null).contains(configParam));
        } catch (final SQLException ex) {
            ex.printStackTrace();
            assert (false);
        }
    }

}
