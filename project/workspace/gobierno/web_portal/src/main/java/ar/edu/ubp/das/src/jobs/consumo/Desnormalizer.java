package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.src.consumers.daos.MSConsumerDao;
import ar.edu.ubp.das.src.consumers.forms.ConsumerForm;
import ar.edu.ubp.das.src.consumers.managers.ConsumerManager;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSCuotasDao;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotasForm;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;
import ar.edu.ubp.das.src.estado_cuentas.managers.CuotasManager;
import ar.edu.ubp.das.src.estado_cuentas.managers.EstadoCuentasManager;
import ar.edu.ubp.das.src.usuarios.daos.MSUsuariosDao;
import ar.edu.ubp.das.src.usuarios.forms.UsuarioForm;
import ar.edu.ubp.das.src.usuarios.managers.UsuarioManager;
import beans.NotificationUpdate;

import java.sql.SQLException;

import static ar.edu.ubp.das.src.utils.Constants.ROL_CONSUMER;

public class Desnormalizer {
    private ConsumerManager consumerManager;
    private UsuarioManager usuarioManager;
    private EstadoCuentasManager estadoCuentasManager;
    private CuotasManager cuotasManager;

    public Desnormalizer(final DatasourceConfig datasourceConfig) {
        final MSConsumerDao msConsumerDao = new MSConsumerDao();
        msConsumerDao.setDatasource(datasourceConfig);
        this.consumerManager = new ConsumerManager(msConsumerDao);

        final MSCuotasDao msCuotasDao = new MSCuotasDao();
        msCuotasDao.setDatasource(datasourceConfig);
        this.cuotasManager = new CuotasManager(msCuotasDao);

        final MSEstadoCuentasDao msEstadoCuentasDao = new MSEstadoCuentasDao();
        msEstadoCuentasDao.setDatasource(datasourceConfig);
        this.estadoCuentasManager = new EstadoCuentasManager(msEstadoCuentasDao);

        final MSUsuariosDao msUsuariosDao = new MSUsuariosDao();
        msUsuariosDao.setDatasource(datasourceConfig);
        this.usuarioManager = new UsuarioManager(msUsuariosDao);
    }

    /**
     * @param concesionariaId
     * @param update
     * @throws SQLException
     */
    public void updateDb(final Long concesionariaId, final NotificationUpdate update) throws SQLException {
        updateConsumerDb(update);
        final EstadoCuentasForm upserted = updateEstadoCuentaDb(update, concesionariaId);
        updateCuotaDb(update, upserted.getId());
    }

    /**
     * @param update
     * @throws SQLException
     */
    public void updateConsumerDb(final NotificationUpdate update) throws SQLException {

        final ConsumerForm consumer = new ConsumerForm();
        consumer.setDocumento(update.getClienteDocumento());

        if (!consumerManager.getDao().valid(consumer)) {

            final UsuarioForm usuarioForm = new UsuarioForm();
            usuarioForm.setDocumento(update.getClienteDocumento());
            usuarioForm.setRol(ROL_CONSUMER);
            usuarioManager.getDao().insert(usuarioForm);

            consumer.setNombre(update.getClienteNombre());
            consumer.setApellido(update.getClienteApellido());
            consumer.setNroTelefono(update.getClienteNroTelefono());
            consumer.setEmail(update.getClienteEmail());
            consumerManager.getDao().insert(consumer);
        }
    }

    /**
     * @param update
     * @param concesionariaId
     * @throws SQLException
     */
    public EstadoCuentasForm updateEstadoCuentaDb(final NotificationUpdate update, final Long concesionariaId) throws SQLException {

        final EstadoCuentasForm estadoCuenta = new EstadoCuentasForm();
        estadoCuenta.setConcesionariaId(concesionariaId);
        estadoCuenta.setNroPlanConcesionaria(update.getPlanId());
        estadoCuenta.setDocumentoCliente(update.getClienteDocumento());
        estadoCuenta.setVehiculo(update.getVehiculoId());
        estadoCuenta.setFechaAltaConcesionaria(update.getPlanFechaAlta());
        estadoCuenta.setEstado(update.getPlanEstado()); // !!!
        return estadoCuentasManager.getDao().upsert(estadoCuenta);
    }

    /**
     * @param update
     * @throws SQLException
     */
    public void updateCuotaDb(final NotificationUpdate update, final Long estadoCuentaId) throws SQLException {

        final CuotasForm cuota = new CuotasForm();
        cuota.setEstadoCuentaId(estadoCuentaId);
        cuota.setNroCuota(update.getCuotaNroCuota());
        cuota.setFechaVencimiento(update.getCuotaFechaVencimiento());
        cuota.setMonto(update.getCuotaMonto());
        cuota.setFechaPago(update.getCuotaFechaPago());
        cuota.setFechaAltaConcesionaria(update.getCuotaFechaAlta());
        cuotasManager.getDao().upsert(cuota);
    }
}
