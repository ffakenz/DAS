package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSConsumoDao;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSConsumoResultDao;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSJobConsumoDao;

public class ConsumoJobManager {

    private MSConsumoDao msConsumoDao;
    private MSConsumoResultDao msConsumoResultDao;
    private MSJobConsumoDao msJobConsumoDao;

    public ConsumoJobManager(final DatasourceConfig datasourceConfig) {
        this.msConsumoDao = new MSConsumoDao();
        this.msConsumoDao.setDatasource(datasourceConfig);
        this.msConsumoResultDao = new MSConsumoResultDao();
        this.msConsumoResultDao.setDatasource(datasourceConfig);
        this.msJobConsumoDao = new MSJobConsumoDao();
        this.msJobConsumoDao.setDatasource(datasourceConfig);
    }

    public MSConsumoDao getMsConsumoDao() {
        return msConsumoDao;
    }

    public MSConsumoResultDao getMsConsumoResultDao() {
        return msConsumoResultDao;
    }

    public MSJobConsumoDao getMsJobConsumoDao() {
        return msJobConsumoDao;
    }
}