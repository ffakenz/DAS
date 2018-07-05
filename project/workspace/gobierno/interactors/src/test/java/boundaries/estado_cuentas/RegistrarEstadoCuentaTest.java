package boundaries.estado_cuentas;

import beans.EstadoCuentaForm;
import estado_cuentas.EstadoCuentaInteractor;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import mocks.MSEstadoCuentaDaoMock;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarEstadoCuentaTest {

    @Test
    public void validarRegistrarSuccessfully() {
        final RegistrarEstadoCuenta registador = new EstadoCuentaInteractor();
        final MSEstadoCuentaDaoMock estadoCuentaDao = new MSEstadoCuentaDaoMock();

        final EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        final Optional<Long> estadoCuentaId = registador.registrarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        assertEquals(Optional.of(Long.valueOf(1)), estadoCuentaId);

        assertEquals(true, estadoCuentaDao.estadoCuentas.contains(estadoCuenta));
    }
}