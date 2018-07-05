package boundaries.estado_cuentas;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.EstadoCuentaForm;
import estado_cuentas.EstadoCuentaInteractor;
import estado_cuentas.boundaries.ConsultarEstadoCuentas;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import mocks.MSEstadoCuentaDaoMock;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ConsultarEstadoCuentasTest {

    @Test
    public void testConsultarEstadoCuentasSuccessfully() {
        final Dao estadoCuentaDao = new MSEstadoCuentaDaoMock();

        final ConsultarEstadoCuentas consultor = new EstadoCuentaInteractor();
        final List<EstadoCuentaForm> estadosDeCuenta =
                consultor.consultarEstadoCuentas().apply(estadoCuentaDao);

        assertEquals(true, estadosDeCuenta.isEmpty());

        final RegistrarEstadoCuenta registrador = (RegistrarEstadoCuenta) consultor;
        final EstadoCuentaForm estadoCuentaMock = new EstadoCuentaForm();
        estadoCuentaMock.setConcesionariaId(Long.valueOf(1));
        estadoCuentaMock.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuentaMock.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuentaMock.setVehiculo(Long.valueOf(1));
        estadoCuentaMock.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        final Optional<Long> estadoCuentaId =
                registrador.registrarEstadoCuenta(estadoCuentaMock).apply(estadoCuentaDao);

        assertEquals(Optional.of(Long.valueOf(1)), estadoCuentaId);

        final List<EstadoCuentaForm> estadosDeCuenta2 =
                consultor.consultarEstadoCuentas().apply(estadoCuentaDao);
        assertEquals(true, estadosDeCuenta2.contains(estadoCuentaMock));
    }
}
