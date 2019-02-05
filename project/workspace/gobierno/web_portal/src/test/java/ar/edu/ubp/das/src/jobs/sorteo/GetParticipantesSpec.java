package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.jobs.consumo.Desnormalizer;
import ar.edu.ubp.das.src.jobs.sorteo.daos.MSParticipanteDao;
import ar.edu.ubp.das.src.jobs.sorteo.forms.ParticipanteForm;
import beans.CuotaBean;
import beans.NotificationUpdate;
import beans.PlanBean;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;
import util.scenarios.ConsumoJobScenarios;
import util.scenarios.ISorteoSpec;

import java.sql.SQLException;
import java.util.List;

import static clients.factory.ClientType.AXIS;
import static org.junit.Assert.assertEquals;

public class GetParticipantesSpec implements ISorteoSpec {

    private ConsumoJobScenarios consumoJobScenarios;
    private DatasourceConfig dataSourceConfig;
    private Desnormalizer desnormalizer;
    private MSParticipanteDao msParticipanteDao;

    @Before
    public void setup() throws SQLException {

        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpDBGetAllAprobadasDesactualizadas();

        this.dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        consumoJobScenarios = new ConsumoJobScenarios(dataSourceConfig);

        this.desnormalizer = new Desnormalizer(dataSourceConfig);

        this.msParticipanteDao = new MSParticipanteDao();
        this.msParticipanteDao.setDatasource(dataSourceConfig);
    }


    @Test
    public void test_00() throws SQLException {

        // 2 estados de cuenta
        final ConcesionariaForm concesionariaForm1 = consumoJobScenarios.setConcesionaria(AXIS, true);

        buildPlanScenario(concesionariaForm1.getId(), 1L, 4, 1, 2, 3, 4);
        buildPlanScenario(concesionariaForm1.getId(), 2L, 4, 1, 2, 3, 4);

        final List<ParticipanteForm> participantes = this.msParticipanteDao.getParticipantes(1, 6);
        assertEquals(2, participantes.size());
    }

    @Test
    public void test_01() throws SQLException {

        // 2 estados de cuenta participantes, y 2 que no
        final ConcesionariaForm concesionariaForm1 = consumoJobScenarios.setConcesionaria(AXIS, true);

        // estados de cuentas que deberian participar
        buildPlanScenario(concesionariaForm1.getId(), 1L, 3, 1, 2, 3);
        buildPlanScenario(concesionariaForm1.getId(), 2L, 3, 1, 2, 3);
        // estados de cuentas que no participarian
        buildPlanScenario(concesionariaForm1.getId(), 3L, 3, 1);
        buildPlanScenario(concesionariaForm1.getId(), 4L, 5, 1, 2, 3, 4, 5);

        final List<ParticipanteForm> participantes = this.msParticipanteDao.getParticipantes(2, 4);
        assertEquals(2, participantes.size());
    }

    @Test
    public void test_02() throws SQLException {

        final ConcesionariaForm concesionariaForm1 = consumoJobScenarios.setConcesionaria(AXIS, true);

        // 1 estado cuenta (cuotas pagas en rango, pero no está al día)
        buildPlanScenario(concesionariaForm1.getId(), 1L, 4, 1, 2, 3);
        // 1 estado cuenta participante (cuotas en rango y al dia)
        buildPlanScenario(concesionariaForm1.getId(), 2L, 3, 1, 2, 3);
        // 1 (estado cuenta (cuotas fuera de rango, y no esta al dia)
        buildPlanScenario(concesionariaForm1.getId(), 3L, 3, 1);
        // 1 estado cuenta (al dia pero con cuotas fuera de rango)
        buildPlanScenario(concesionariaForm1.getId(), 4L, 5, 1, 2, 3, 4, 5);

        final List<ParticipanteForm> participantes = this.msParticipanteDao.getParticipantes(2, 4);
        assertEquals(1, participantes.size());
    }

    private void buildPlanScenario(final Long concesionariaId, final Long planId, final Integer cantCuotas, final Integer... cuotasPagas) throws SQLException {
        final List<CuotaBean> cuotas = new CuotaBeanBuilder(cantCuotas, cuotasPagas).build();
        final PlanBean planBean = new PlanBeanBuilder(planId, cuotas).build();
        final List<NotificationUpdate> notificationUpdates = planBean.toNotificationUpdates();
        for (final NotificationUpdate update : notificationUpdates) {
            desnormalizer.updateDb(concesionariaId, update);
        }
    }
}
