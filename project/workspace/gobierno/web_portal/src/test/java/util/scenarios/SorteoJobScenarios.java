package util.scenarios;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigTecnoParamDao;
import ar.edu.ubp.das.src.concesionarias.daos.MSConfigurarConcesionariaDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.concesionarias.managers.ConfigurarConcesionariaManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJobManager;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.utils.DateUtils;

import java.sql.SQLException;

public class SorteoJobScenarios {

    private MSConsumerDao msConsumerDao;
    private MSUsuariosDao msUsuariosDao;
    private MSEstadoCuentasDao estadoCuentaDao;
    private MSCuotasDao cuotasDao;
    private MSConcesionariasDao msConcesionariasDao;
    private MSConcesionariasDao concesionariasManager;
    private MSConfigurarConcesionariaDao msConfigConcDao = new MSConfigurarConcesionariaDao();
    private MSConfigurarConcesionariaDao configurarConcManager;
    private MSConfigTecnoParamDao msConfigTecnoParamDao;
    private SorteoJobManager sorteoJobManager;


    public SorteoJobScenarios(DatasourceConfig dataSourceConfig) {
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

        sorteoJobManager = new SorteoJobManager(dataSourceConfig);

    }

    public void setSorteoNuevoParaHoy() throws SQLException {
        SorteoForm sorteoForm = new SorteoForm();
        sorteoForm.setEstado(EstadoSorteo.NUEVO);
        sorteoForm.setFechaEjecucion(DateUtils.getDayDate());

        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);
    }

    public void approveRestConcesionaria() throws SQLException {
        ConcesionariaForm concesionariaForm = concesionariasManager.selectById(1L).orElse(null);
        concesionariaForm.setCodigo("CODIGO_SECRETO");
        concesionariasManager.approveConcesionaria(concesionariaForm);
    }
}