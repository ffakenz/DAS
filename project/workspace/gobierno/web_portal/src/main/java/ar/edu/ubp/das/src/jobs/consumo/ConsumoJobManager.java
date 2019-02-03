package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSConsumoDao;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSConsumoResultDao;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSJobConsumoDao;
import ar.edu.ubp.das.src.jobs.consumo.daos.MSNotificationUpdateDao;

public class ConsumoJobManager {

    private MSConsumoDao msConsumoDao;
    private MSConsumoResultDao msConsumoResultDao;
    private MSJobConsumoDao msJobConsumoDao;
    private MSNotificationUpdateDao msNotificationUpdateDao;

    public ConsumoJobManager(final DatasourceConfig datasourceConfig) {
        this.msConsumoDao = new MSConsumoDao();
        this.msConsumoDao.setDatasource(datasourceConfig);
        this.msConsumoResultDao = new MSConsumoResultDao();
        this.msConsumoResultDao.setDatasource(datasourceConfig);
        this.msJobConsumoDao = new MSJobConsumoDao();
        this.msJobConsumoDao.setDatasource(datasourceConfig);
        this.msNotificationUpdateDao = new MSNotificationUpdateDao();
        this.msNotificationUpdateDao.setDatasource(datasourceConfig);
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

    public MSNotificationUpdateDao getMsNotificationUpdateDao() {
        return msNotificationUpdateDao;
    }
}
