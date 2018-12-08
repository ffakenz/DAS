package ar.edu.ubp.das.src.jobs;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.jobs.consumoo.ConsumerJob;
import beans.NotificationUpdate;
import clients.ConcesionariaServiceContract;
import clients.IClientFactory;
import clients.factory.ClientType;
import org.junit.Before;
import org.junit.Test;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class ConsumerJobSpec {
    // Daos
    private DatasourceConfig dataSourceConfig;
    private MSConsumerDao msConsumerDao;
    private MSEstadoCuentasDao estadoCuentaDao;
    private MSCuotasDao cuotasDao;

    // Mocks
    private NotificationUpdate existingConsumer;
    private NotificationUpdate nonExistingConsumer;
    private NotificationUpdate existingEstadoCuenta;
    private NotificationUpdate nonExistingEstadoCuenta;
    private NotificationUpdate existingCuota;
    private NotificationUpdate nonExistingCuota;

    class EmptyClientFactoryMock implements IClientFactory {
        @Override
        public Optional<ConcesionariaServiceContract> getClientFor(final ClientType configTecno, final Map<String, String> params) {
            return Optional.of(new ConcesionariaServiceContract() {
                @Override
                public List<NotificationUpdate> consultarPlanes(final String offset) {
                    return null;
                }

                @Override
                public NotificationUpdate consultarPlan(final Long planId) {
                    return null;
                }

                @Override
                public void cancelarPlan(final Long planId) {

                }
            });
        }
    }

    @Before
    public void setup() throws SQLException {
        // Clean DB
        TestDB.getInstance().cleanDB();
        TestDB.getInstance().setUpEmptyDB();

        // Setup Daos
        this.dataSourceConfig = TestDB.getInstance().getDataSourceConfig();
        msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(dataSourceConfig);
        estadoCuentaDao = new MSEstadoCuentasDao();
        estadoCuentaDao.setDatasource(dataSourceConfig);
        cuotasDao = new MSCuotasDao();
        cuotasDao.setDatasource(dataSourceConfig);

        // Setup Mocks
        this.existingConsumer = new NotificationUpdate();
        this.nonExistingConsumer = new NotificationUpdate();
        this.existingEstadoCuenta = new NotificationUpdate();
        this.nonExistingEstadoCuenta = new NotificationUpdate();
        this.existingCuota = new NotificationUpdate();
        this.nonExistingCuota = new NotificationUpdate();
    }

    // Setup Helper methods
    private void setUpConsumer(final NotificationUpdate consumer,
                               final Long documento,
                               final String nombre,
                               final String apellido,
                               final String nroTelefono,
                               final String email) {
        consumer.setClienteDocumento(documento);
        consumer.setClienteNombre(nombre);
        consumer.setClienteApellido(apellido);
        consumer.setClienteNroTelefono(nroTelefono);
        consumer.setClienteEmail(email);
    }

    private void setUpEstadoCuenta(final NotificationUpdate consumer,
                                   final Long nroPlanConcesionaria,
                                   final Long dniConsumer,
                                   final Long vehiculo,
                                   final Timestamp fechaAltaConcesionaria,
                                   final String estado) {
        consumer.setPlanId(nroPlanConcesionaria);
        consumer.setClienteDocumento(dniConsumer);
        consumer.setVehiculoId(vehiculo);
        consumer.setPlanFechaAlta(fechaAltaConcesionaria);
        consumer.setPlanEstado(estado);
    }

    private void setUpCuota(final NotificationUpdate consumer,
                            final Long nroCuota,
                            final Long estadoCuentaId,
                            final Timestamp fechaVencimiento,
                            final Integer monto,
                            final Timestamp fechaPago) {
        consumer.setPlanId(estadoCuentaId);
        consumer.setCoutaNroCuota(nroCuota);
        consumer.setCoutaFechaVencimiento(fechaVencimiento);
        consumer.setCoutaMonto(monto);
        consumer.setCoutaFechaPago(fechaPago);
    }

    // UPDATE CONSUMER DB

    // We should insert a Consumer if it does not exists
    @Test
    public void test_ConsumerJob_UpdateConsumer_success() throws Exception {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new EmptyClientFactoryMock());
        setUpConsumer(nonExistingConsumer,
                777L,
                "ChuPamela",
                "Anderson",
                "69039857123",
                "pamelaanderson@mail.com");

        // verify it does not exists in db
        final ConsumerForm form = new ConsumerForm();
        form.setDocumento(777L);
        form.setConcesionaria(7L);
        final Optional<ConsumerForm> consumerForm =
                msConsumerDao.selectConsumerByDniAndConcesionaria(form);
        assertFalse(consumerForm.isPresent());
        // updateConsumerDb
        consumer.updateConsumerDb(nonExistingConsumer, 7L);
        // verify it exists in db
        final Optional<ConsumerForm> consumerForm2 =
                msConsumerDao.selectConsumerByDniAndConcesionaria(form);
        assertTrue(consumerForm2.isPresent());
    }

    // We should not insert/update a Consumer if it exists
    @Test
    public void test_ConsumerJob_UpdateConsumer_failure_insert() throws Exception {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new EmptyClientFactoryMock());
        setUpConsumer(existingConsumer,
                666L,
                "Pamela",
                "Anderson",
                "35156345677",
                "pamelaanderson@mail.com");


        // verify it exists in db
        final ConsumerForm form = new ConsumerForm();
        form.setDocumento(777L);
        form.setConcesionaria(7L);
        final Optional<ConsumerForm> consumerForm =
                msConsumerDao.selectConsumerByDniAndConcesionaria(form);
        assertTrue(consumerForm.isPresent());
        // updateConsumerDb
        consumer.updateConsumerDb(existingConsumer, 5L);
        // verify it exists in db and is the same
        final Optional<ConsumerForm> consumerForm2 =
                msConsumerDao.selectConsumerByDniAndConcesionaria(form);
        assertTrue(consumerForm2.isPresent());
        assertEquals(consumerForm.get(), consumerForm2.get());
    }

    // UPDATE ESTADO CUENTA DB

    // We should insert a EstadoCuenta if it does not exists
    @Test
    public void test_ConsumerJob_NEW_UpdateEstadoCuenta_success() throws Exception {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new EmptyClientFactoryMock());
        setUpEstadoCuenta(nonExistingEstadoCuenta,
                1006L,
                555L,
                2L,
                Timestamp.valueOf("2018-03-03 23:58:02"),
                "inicial");

        // verify it does not exists in db
        final EstadoCuentasForm form = new EstadoCuentasForm();
        form.setConcesionariaId(3L);
        form.setNroPlanConcesionaria(1006L);
        final Optional<EstadoCuentasForm> estadoCuentasForm =
                estadoCuentaDao.selectByNroPlanAndConcesionaria(form);
        assertFalse(estadoCuentasForm.isPresent());
        // updateEstadoCuentaDb
        consumer.updateEstadoCuentaDb(nonExistingEstadoCuenta, 3L);
        // verify it exists in db
        final Optional<EstadoCuentasForm> estadoCuentasForm2 =
                estadoCuentaDao.selectByNroPlanAndConcesionaria(form);
        assertTrue(estadoCuentasForm2.isPresent());
    }

    // We should update a EstadoCuenta if it exists
    @Test
    public void test_ConsumerJob_OLD_UpdateEstadoCuenta_success() throws Exception {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new EmptyClientFactoryMock());
        setUpEstadoCuenta(existingEstadoCuenta,
                1001L,
                111L,
                1L,
                Timestamp.valueOf("2018-01-01 21:58:01"),
                "cancelado");

        // verify it exists in db
        final EstadoCuentasForm form = new EstadoCuentasForm();
        form.setConcesionariaId(1L);
        form.setNroPlanConcesionaria(1001L);
        final Optional<EstadoCuentasForm> estadoCuentasForm =
                estadoCuentaDao.selectByNroPlanAndConcesionaria(form);
        assertTrue(estadoCuentasForm.isPresent());
        // updateEstadoCuentaDb
        consumer.updateEstadoCuentaDb(existingEstadoCuenta, 1L);
        // verify it exists in db and it changed
        final Optional<EstadoCuentasForm> estadoCuentasForm2 =
                estadoCuentaDao.selectByNroPlanAndConcesionaria(form);
        assertTrue(estadoCuentasForm2.isPresent());
        assertNotEquals(estadoCuentasForm.get(), estadoCuentasForm2.get());
    }

    // UPDATE CUOTA DB

    // We should insert a Cuota if it does not exists
    @Test
    public void test_ConsumerJob_NEW_Cuota_success() throws Exception {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new EmptyClientFactoryMock());
        setUpCuota(nonExistingCuota,
                4L,
                4L,
                Timestamp.valueOf("2018-07-01 21:58:01"),
                null,
                null);

        // verify it does not exists in db
        final CuotasForm form = new CuotasForm();
        form.setNroCuota(4L);
        form.setEstadoCuentaId(4L);
        final Optional<CuotasForm> cuotasForm = cuotasDao.selectCuota(form);
        assertFalse(cuotasForm.isPresent());
        // updateCuotaDb
        consumer.updateCuotaDb(nonExistingCuota);
        // verify it exists in db
        final Optional<CuotasForm> cuotasForm2 = cuotasDao.selectCuota(form);
        assertTrue(cuotasForm2.isPresent());
    }

    // We should update a Cuota if it exists
    @Test
    public void test_ConsumerJob_OLD_Cuota_success() throws Exception {
        final ConsumerJob consumer = new ConsumerJob(dataSourceConfig, new EmptyClientFactoryMock());
        setUpCuota(existingCuota,
                3L,
                2L,
                Timestamp.valueOf("2018-04-01 21:58:01"),
                10000,
                Timestamp.valueOf("2018-03-21 22:58:01"));

        // verify it exists in db
        final CuotasForm form = new CuotasForm();
        form.setNroCuota(3L);
        form.setEstadoCuentaId(2L);
        final Optional<CuotasForm> cuotasForm = cuotasDao.selectCuota(form);
        assertTrue(cuotasForm.isPresent());
        // updateCuotaDb
        consumer.updateCuotaDb(existingCuota);
        // verify it exists in db and it changed
        final Optional<CuotasForm> cuotasForm2 = cuotasDao.selectCuota(form);
        assertTrue(cuotasForm2.isPresent());
        assertNotEquals(cuotasForm.get(), cuotasForm2.get());
    }
}
