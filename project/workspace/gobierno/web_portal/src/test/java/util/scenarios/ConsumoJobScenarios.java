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
import java.util.List;
import java.util.UUID;

import static clients.factory.ClientType.CXF;
import static clients.factory.ClientType.REST;

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

    public ConcesionariaForm setConcesionaria(ClientType clientType, Boolean isApproved) throws SQLException {

        ConcesionariaForm concesionariaForm = registrarConcesionaria();

        if(isApproved) {
            aprobarConcesionaria(concesionariaForm, UUID.randomUUID().toString());
            configurarConcesionaria(concesionariaForm, clientType);
            return concesionariasManager.selectById(concesionariaForm.getId()).get();
        }

        return concesionariaForm;
    }

    public void configurarConcesionaria(ConcesionariaForm concesionariaForm, ClientType clientType) throws SQLException {

        List<ConfigurarConcesionariaForm> configConcesionariaList;

        if(REST == clientType)
            configConcesionariaList = Mocks.INSTANCE.getConfigRest(concesionariaForm.getId());
        else if (CXF == clientType)
            configConcesionariaList = Mocks.INSTANCE.getConfigCxf(concesionariaForm.getId());
        else
            configConcesionariaList = Mocks.INSTANCE.getConfigAxis(concesionariaForm.getId());

        ConfigTecnoXConcesionariaForm configTecnoXConcesionaria =
                Mocks.INSTANCE.getConfigTecnoXConcesionaria(concesionariaForm.getId(), clientType.getName());

        msConfigTecnoParamDao.insert(configTecnoXConcesionaria);

        for (ConfigurarConcesionariaForm c : configConcesionariaList) {
            configurarConcManager.upsert(c);
        }
    }

    public void aprobarConcesionaria(ConcesionariaForm concesionariaForm, String codigo) throws SQLException {

        concesionariaForm.setCodigo(codigo);
        concesionariasManager.approveConcesionaria(concesionariaForm);
    }

    public ConcesionariaForm registrarConcesionaria() throws SQLException {
        ConcesionariaForm concesionariaFormFiat = Mocks.INSTANCE.getConcesionariaFormFiat();
        concesionariasManager.insert(concesionariaFormFiat);

        ConcesionariaForm concesionariaForm = concesionariasManager.selectByCuit(concesionariaFormFiat).get();

        return concesionariaForm;
    }
}