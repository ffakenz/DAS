package boundaries.estado_cuentas;

import ar.edu.ubp.das.src.estado_cuentas.EstadoCuentaInteractor;
import ar.edu.ubp.das.src.estado_cuentas.boundaries.ConsultarEstadoCuentas;
import ar.edu.ubp.das.src.estado_cuentas.boundaries.RegistrarEstadoCuenta;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentaDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ConsultarEstadoCuentasTest {

    MSEstadoCuentaDao estadoCuentaDao = new MSEstadoCuentaDao();

    @Test
    public void testConsultarEstadoCuentasSuccessfully() {

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
