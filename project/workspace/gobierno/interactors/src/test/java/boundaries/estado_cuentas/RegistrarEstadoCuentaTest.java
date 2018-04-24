package boundaries.estado_cuentas;

import estado_cuentas.EstadoCuentaInteractor;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import estado_cuentas.forms.EstadoCuentaForm;
import mocks.EstadoCuentaDaoMock;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class RegistrarEstadoCuentaTest {

    @Test
    public void validarRegistrarSuccessfully(){
        RegistrarEstadoCuenta registador = new EstadoCuentaInteractor();
        EstadoCuentaDaoMock estadoCuentaDao = new EstadoCuentaDaoMock();

        EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculoId(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        Optional<Long> estadoCuentaId = registador.registrarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        assertEquals(Optional.of(1), estadoCuentaId);

        assertEquals(true, estadoCuentaDao.estadoCuentas.contains(estadoCuenta));

    }
}