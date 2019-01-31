package util.scenarios;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigTecnoParamDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoXConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import clients.factory.ClientType;
import util.Mocks;

import java.sql.SQLException;

public class ConsumoJobScenarios {

    private MSConsumerDao msConsumerDao;
    private MSUsuariosDao msUsuariosDao;
    private MSEstadoCuentasDao estadoCuentaDao;
    private MSCuotasDao cuotasDao;
    private MSConcesionariasDao msConcesionariasDao;
    private MSConcesionariasDao concesionariasManager;
    private MSConfigurarConcesionariaDao msConfigConcDao = new MSConfigurarConcesionariaDao();
    private MSConfigurarConcesionariaDao configurarConcManager;
    private MSConfigTecnoParamDao msConfigTecnoParamDao;

    public ConsumoJobScenarios(DatasourceConfig dataSourceConfig) {
        msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(dataSourceConfig);

        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

        estadoCuentaDao = new MSEstadoCuentasDao();
        estadoCuentaDao.setDatasource(dataSourceConfig);

        cuotasDao = new MSCuotasDao();
        cuotasDao.setDatasource(dataSourceConfig);

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

        concesionariasManager = new ConcesionariasManager(msConcesionariasDao).getDao();

        msConfigConcDao = new MSConfigurarConcesionariaDao();
        msConfigConcDao.setDatasource(dataSourceConfig);

        configurarConcManager = new ConfigurarConcesionariaManager(msConfigConcDao).getDao();

        msConfigTecnoParamDao = new MSConfigTecnoParamDao();
        msConfigTecnoParamDao.setDatasource(dataSourceConfig);

    }

    public void oneRestConcesionaria() throws SQLException {

        ConcesionariaForm concesionariaFormFiat = Mocks.INSTANCE.getConcesionariaFormFiat();

        concesionariasManager.insert(concesionariaFormFiat);

        concesionariaFormFiat.setId(1L);
        concesionariaFormFiat.setCodigo("CODIGO_1");

        concesionariasManager.approveConcesionaria(concesionariaFormFiat);

        ConfigurarConcesionariaForm configRest = Mocks.INSTANCE.getConfigRest(concesionariaFormFiat.getId());

        ConfigTecnoXConcesionariaForm configTecnoXConcesionaria =
                Mocks.INSTANCE.getConfigTecnoXConcesionaria(concesionariaFormFiat.getId(), ClientType.REST.getName());

        msConfigTecnoParamDao.insert(configTecnoXConcesionaria);

        configurarConcManager.upsert(configRest);
    }
}