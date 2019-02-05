package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;
import java.util.List;

public class GetAprobadasDesactualizadasSpec {

    MSConcesionariasDao msConcesionariasDao;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDBGetAllAprobadasDesactualizadas();

        final DatasourceConfig dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(dataSourceConfig);

    }

    @Test
    public void test_00() throws SQLException {
        //
        final List<ConcesionariaForm> concesionariaForms = msConcesionariasDao.selectAprobadasDesactualizadas(5);

    }
}
