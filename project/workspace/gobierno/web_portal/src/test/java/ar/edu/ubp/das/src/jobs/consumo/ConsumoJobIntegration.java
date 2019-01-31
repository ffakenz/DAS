package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigTecnoParamsForm;
import ar.edu.ubp.das.src.concesionarias.forms.ConfigurarConcesionariaForm;
import ar.edu.ubp.das.src.concesionarias.managers.ConcesionariasManager;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import beans.NotificationUpdate;
import clients.factory.ClientType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.TestDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConsumoJobIntegration {

    // Daos
    private DatasourceConfig dataSourceConfig;
    private MSConsumerDao msConsumerDao;
    private MSUsuariosDao msUsuariosDao;
    private MSEstadoCuentasDao estadoCuentaDao;
    private MSCuotasDao cuotasDao;

    // Mocks
    private NotificationUpdate existingConsumer;
    private NotificationUpdate nonExistingConsumer;
    private NotificationUpdate existingEstadoCuenta;
    private NotificationUpdate nonExistingEstadoCuenta;
    private NotificationUpdate existingCuota;
    private NotificationUpdate nonExistingCuota;

    class Scenario {
        private DatasourceConfig dataSourceConfig;
        private MSConsumerDao msConsumerDao;
        private MSUsuariosDao msUsuariosDao;
        private MSEstadoCuentasDao estadoCuentaDao;
        private MSCuotasDao cuotasDao;

        public Scenario(DatasourceConfig dataSourceConfig) {
            msConsumerDao = new MSConsumerDao();
            msConsumerDao.setDatasource(dataSourceConfig);

            msUsuariosDao = new MSUsuariosDao();
            msUsuariosDao.setDatasource(dataSourceConfig);

            estadoCuentaDao = new MSEstadoCuentasDao();
            estadoCuentaDao.setDatasource(dataSourceConfig);

            cuotasDao = new MSCuotasDao();
            cuotasDao.setDatasource(dataSourceConfig);
        }
        public void executeScenario() throws SQLException {

            DaoImpl msConcesionariasDao = new MSConcesionariasDao();
            ConcesionariasManager concesionariasManager = new ConcesionariasManager(msConcesionariasDao);

            ConcesionariaForm concesionariaForm = new ConcesionariaForm();
            concesionariaForm.setCodigo("APROBADA");
            concesionariaForm.setCuit("20-93337511-1");
            concesionariaForm.setEmail("concesionaria_fiat@gmail.com");
            concesionariaForm.setDireccion("direccion concesionaria fiat 123");
            concesionariaForm.setFechaAlta(Timestamp.from(Instant.now()));
            concesionariaForm.setFechaRegistracion(Timestamp.from(Instant.now()));
            concesionariaForm.setId(1L);
            concesionariaForm.setNombre("Concesionaria Fiat");
            concesionariaForm.setTel("351-7696448");

            concesionariasManager.getDao().insert(concesionariaForm);

            ConfigurarConcesionariaForm configConcesionariaForm = new ConfigurarConcesionariaForm();
            configConcesionariaForm.setConcesionariaId(1L);
            configConcesionariaForm.setConfigParam(ClientType.REST.getName());
            configConcesionariaForm.setConfigTecno();
            configConcesionariaForm.setValue();

        }
    }


    @Before
    public void setup() throws SQLException {
        // Clean DB
        TestDB.getInstance().cleanDB();

        // Setup Daos
        this.dataSourceConfig = TestDB.getInstance().getDataSourceConfig();

        msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(dataSourceConfig);

        msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(dataSourceConfig);

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
                            final Timestamp fechaAltaConcesionaria,
                            final Timestamp fechaVencimiento,
                            final Integer monto,
                            final Timestamp fechaPago) {
        consumer.setPlanId(estadoCuentaId);
        consumer.setCuotaNroCuota(nroCuota);
        consumer.setCuotaFechaAlta(fechaAltaConcesionaria);
        consumer.setCuotaFechaVencimiento(fechaVencimiento);
        consumer.setCuotaMonto(monto);
        consumer.setCuotaFechaPago(fechaPago);
    }

    // UPDATE CONSUMER DB
    @Test
    public void it_test_10_() throws Exception {
//        final ConsumoJob consumer = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());
//
//        Long doc = 2L;
//
//        setUpConsumer(nonExistingConsumer,
//                doc,
//                "Nombre_test_2",
//                "Apellido_test_2",
//                "telefono_test_2",
//                "test_2@test.com");
//
//
//        final ConsumerForm form = new ConsumerForm();
//        form.setDocumento(doc);
//
//        final Optional<ConsumerForm> consumerForm = msConsumerDao.selectConsumerByDni(form);
//        assertFalse(consumerForm.isPresent());
//
//        // updateConsumerDb
//        consumer.updateConsumerDb(nonExistingConsumer);
//
//        // verify it exists in db
//        final Optional<ConsumerForm> consumerForm2 = msConsumerDao.selectConsumerByDni(form);
//        final Optional<UsuarioForm> usuarioFormOpt = msUsuariosDao.selectByDocumento(doc);
//
//        assertTrue(consumerForm2.isPresent());
//        assertTrue(usuarioFormOpt.isPresent());
    }

}
