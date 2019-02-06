package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.jobs.ClientFactoryAdapter;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.DateUtils;
import clients.factory.ClientFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.util.Optional;

import static ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo.COMPLETADO;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SorteoJobSpec {


    private DatasourceConfig datasourceConfig;
    private ClientFactoryAdapter clientFactoryAdapter;
    private SorteoJob sorteoJob;
    private SorteoJobManager sorteoJobManager;
    private ConcesionariasManager concesionariasManager;

    @Before
    public void setup() throws SQLException {
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDB();

        datasourceConfig = TestDB.getInstance().getDataSourceConfig();
        clientFactoryAdapter = new ClientFactoryAdapter(ClientFactory.getInstance());
        sorteoJob = new SorteoJob(datasourceConfig, clientFactoryAdapter);
        sorteoJobManager = new SorteoJobManager(datasourceConfig);
        MSConcesionariasDao msConcesionariasDao = new MSConcesionariasDao();
        msConcesionariasDao.setDatasource(datasourceConfig);
        concesionariasManager = new ConcesionariasManager(msConcesionariasDao);
    }

    @Test
    public void test_01() throws SQLException {
        // without sorteo , base case

        sorteoJob.execute(null);

        assertEquals(0, sorteoJobManager.getMsSorteoDao().select().size());
    }

    @Test
    public void test_02() throws SQLException {
        // without sorteo , base case
        setNuevoParaHoy();

        sorteoJob.execute(null);

        assertEquals(1, sorteoJobManager.getMsSorteoDao().select().size());

        Optional<SorteoForm> sorteoById = sorteoJobManager.getMsSorteoDao().getSorteoById(1L);
        assertTrue(sorteoById.isPresent());
        assertEquals(COMPLETADO, sorteoById.get().getEstadoSorteo());
    }

    private void setNuevoParaHoy() throws SQLException {
        SorteoForm sorteoForm = new SorteoForm();
        sorteoForm.setEstado(EstadoSorteo.NUEVO);
        sorteoForm.setFechaEjecucion(DateUtils.getDayDate());

        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);
    }

}
