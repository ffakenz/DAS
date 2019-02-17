package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSParticipanteDao;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSSorteoDao;

public class SorteoJobManager {

    private MSSorteoDao msSorteoDao;
    private MSParticipanteDao msParticipanteDao;
    private MSConsumerDao msConsumerDao;

    public SorteoJobManager(final DatasourceConfig datasourceConfig) {
        this.msSorteoDao = new MSSorteoDao();
        this.msSorteoDao.setDatasource(datasourceConfig);
        this.msParticipanteDao = new MSParticipanteDao();
        this.msParticipanteDao.setDatasource(datasourceConfig);
        this.msConsumerDao = new MSConsumerDao();
        this.msConsumerDao.setDatasource(datasourceConfig);
    }

    public MSSorteoDao getMsSorteoDao() {
        return msSorteoDao;
    }

    public MSParticipanteDao getMsParticipanteDao() {
        return msParticipanteDao;
    }

    public MSConsumerDao getMsConsumerDao() { return msConsumerDao; }
}
