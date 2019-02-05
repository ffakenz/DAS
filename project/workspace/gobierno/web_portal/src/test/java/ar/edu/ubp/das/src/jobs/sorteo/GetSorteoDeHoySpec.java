package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSSorteoDao;
import ar.edu.ubp.das.src.jobs.sorteo.forms.EstadoSorteo;
import ar.edu.ubp.das.src.jobs.sorteo.forms.SorteoForm;
import ar.edu.ubp.das.src.utils.DateUtils;
import clients.factory.ClientFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetSorteoDeHoySpec {

    // Daos
    private DatasourceConfig dataSourceConfig;
    private MSSorteoDao msSorteoDao;
    private SorteoJobManager sorteoJobManager;
    private SorteoJob sorteoJob;

    @Before
    public void setup() throws SQLException {
        // Clean DB
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpEmptyDB();

        // Setup Daos
        this.dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        sorteoJobManager = new SorteoJobManager(dataSourceConfig);

        msSorteoDao = new MSSorteoDao();
        msSorteoDao.setDatasource(dataSourceConfig);

        sorteoJob = new SorteoJob(dataSourceConfig, ClientFactory.getInstance());
    }

    @Test
    public void test_09() {
        // no hay ningún sorteo en la db
        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertFalse(sorteoDeHoy.isPresent());
    }

    @Test
    public void test_10() throws SQLException {
        // hay sorteos nuevos pero ninguno en la fecha de hoy, y ninguno previo
        SorteoForm sorteoForm = new SorteoForm();

        Date fechaSorteo = DateUtils.getDateFromDays(5);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);

        fechaSorteo = DateUtils.getDateFromDays( 10);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);

        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertFalse(sorteoDeHoy.isPresent());
    }

    @Test
    public void test_11() throws SQLException {
        // hay sorteos previos completados, y sorteos nuevos pero no en la fecha de hoy
        setSorteosViejosCompletados();
        setSorteosNuevosParaMasAdelante();

        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();
        assertFalse(sorteoDeHoy.isPresent());
    }

    @Test
    public void test_12() throws SQLException {
        // hay sorteos previos completados, y hay sorteo nuevo para hoy
        setSorteosViejosCompletados();
        setSorteoNuevoParaHoy();

        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertTrue(sorteoDeHoy.isPresent());
        assertEquals(EstadoSorteo.NUEVO.getTipo(), sorteoDeHoy.get().getEstado());
    }

    @Test
    public void test_13() throws SQLException {
        // hay sorteos previos completados, y hay sorteo nuevo para hoy y mas adelante
        setSorteosViejosCompletados();
        setSorteoNuevoParaHoy();
        setSorteosNuevosParaMasAdelante();

        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertTrue(sorteoDeHoy.isPresent());
        assertEquals(EstadoSorteo.NUEVO.getTipo(), sorteoDeHoy.get().getEstado());
    }

    @Test
    public void test_14() throws SQLException {
        // hay sorteos previos en pendiente_x  y hay sorteos nuevo mas adelante
        setSorteoViejo(EstadoSorteo.PENDIENTE_CANCELACION, -2);
        setSorteosNuevosParaMasAdelante();

        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertTrue(sorteoDeHoy.isPresent());
        assertEquals(EstadoSorteo.PENDIENTE_CANCELACION.getTipo(), sorteoDeHoy.get().getEstado());
    }

    @Test
    public void test_15() throws SQLException {
        // hay sorteos previos en pendiente_x  y hay sorteos nuevo para hoy
        setSorteoViejo(EstadoSorteo.PENDIENTE_CANCELACION, -2);
        setSorteoNuevoParaHoy();

        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertTrue(sorteoDeHoy.isPresent());
        assertEquals(EstadoSorteo.PENDIENTE_CANCELACION.getTipo(), sorteoDeHoy.get().getEstado());
    }

    @Test
    public void test_16() throws SQLException {
        // hay sorteos previos en pendiente_x, hay sorteos nuevos viejos y no hay sorteos para hoy
        setSorteoViejo(EstadoSorteo.PENDIENTE_CANCELACION, -6);
        setSorteoViejo(EstadoSorteo.NUEVO, -4);


        // al ejecutar la primera vez , validamos que retorne un sorteo en estado pendiente
        Optional<SorteoForm> sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertTrue(sorteoDeHoy.isPresent());
        assertEquals(EstadoSorteo.PENDIENTE_CANCELACION.getTipo(), sorteoDeHoy.get().getEstado());

        // al ejecutar por segunda vez, suponiendo que el pendiente anterior termino completado
        // debería invalidarse el sorteo nuevo
        sorteoDeHoy.get().setEstado(EstadoSorteo.COMPLETADO.getTipo());
        sorteoJobManager.getMsSorteoDao().update(sorteoDeHoy.get());

        sorteoDeHoy = sorteoJob.getSorteoDeHoy();

        assertFalse(sorteoDeHoy.isPresent());
        List<SorteoForm> sorteosFallados = sorteoJobManager.getMsSorteoDao().getSorteosByEstado(EstadoSorteo.FALLADO);
        assertEquals(1, sorteosFallados.size());

    }


    /** MOCK ESCENARIOS **/
    private void setSorteoViejo(EstadoSorteo estadoSorteo, long dias) throws SQLException {
        SorteoForm sorteoForm = new SorteoForm();
        sorteoForm.setEstado(estadoSorteo.getTipo());

        Date fechaSorteo = DateUtils.getDateFromDays(dias);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insertWithEstado(sorteoForm);
    }

    private void setSorteosNuevosParaMasAdelante() throws SQLException {

        SorteoForm sorteoForm = new SorteoForm();

        Date fechaSorteo = DateUtils.getDateFromDays(5);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);

        fechaSorteo = DateUtils.getDateFromDays(10);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);
    }

    private void setSorteoNuevoParaHoy() throws SQLException {

        SorteoForm sorteoForm = new SorteoForm();
        sorteoForm.setFechaEjecucion(DateUtils.getDayDate());
        sorteoJobManager.getMsSorteoDao().insert(sorteoForm);
    }

    private void setSorteosViejosCompletados() throws SQLException {

        SorteoForm sorteoForm = new SorteoForm();
        sorteoForm.setEstado(EstadoSorteo.COMPLETADO.getTipo());

        Date fechaSorteo = DateUtils.getDateFromDays(-5);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insertWithEstado(sorteoForm);

        fechaSorteo = DateUtils.getDateFromDays( -10);
        sorteoForm.setFechaEjecucion(fechaSorteo);

        sorteoJobManager.getMsSorteoDao().insertWithEstado(sorteoForm);
    }
}
