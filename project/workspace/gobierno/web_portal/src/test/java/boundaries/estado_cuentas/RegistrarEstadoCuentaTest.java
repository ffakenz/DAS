package boundaries.estado_cuentas;

import ar.edu.ubp.das.src.estado_cuentas.EstadoCuentaInteractor;
import ar.edu.ubp.das.src.estado_cuentas.boundaries.RegistrarEstadoCuenta;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarEstadoCuentaTest {

    final MSEstadoCuentaDao estadoCuentaDao = new MSEstadoCuentaDao();

    @Test
    public void validarRegistrarSuccessfully() throws SQLException {
        final RegistrarEstadoCuenta registador = new EstadoCuentaInteractor();

        final EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        final Optional<Long> estadoCuentaId = registador.registrarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        assertEquals(Optional.of(Long.valueOf(1)), estadoCuentaId);

        assertEquals(true, estadoCuentaDao.select(null).contains(estadoCuenta));
    }
}