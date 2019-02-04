package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSEjecucionesSorteoDao;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSParticipanteDao;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSSorteoDao;

public class SorteoJobManager {

    private MSSorteoDao msSorteoDao;
    private MSEjecucionesSorteoDao msEjecucionesSorteoDao;
    private MSParticipanteDao msParticipanteDao;

    public SorteoJobManager(final DatasourceConfig datasourceConfig) {
        this.msSorteoDao = new MSSorteoDao();
        this.msSorteoDao.setDatasource(datasourceConfig);
        this.msParticipanteDao = new MSParticipanteDao();
        this.msParticipanteDao.setDatasource(datasourceConfig);
        this.msEjecucionesSorteoDao = new MSEjecucionesSorteoDao();
        this.msEjecucionesSorteoDao.setDatasource(datasourceConfig);
    }

    public MSSorteoDao getMsSorteoDao() {
        return msSorteoDao;
    }

    public MSParticipanteDao getMsParticipanteDao() {
        return msParticipanteDao;
    }


}
