package boundaries.estado_cuentas;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.EstadoCuentaForm;
import estado_cuentas.EstadoCuentaInteractor;
import estado_cuentas.boundaries.ActualizarEstadoCuenta;
import estado_cuentas.boundaries.ConsultarEstadoCuentas;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import mocks.MSEstadoCuentaDaoMock;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ActualizarEstadoCuentaTest {


    @Test
    public void validateActualizarEstadoCuentaSuccessfully() {

        final Dao estadoCuentaDao = new MSEstadoCuentaDaoMock();
        final RegistrarEstadoCuenta registrador = new EstadoCuentaInteractor();
        final ConsultarEstadoCuentas consultor = (ConsultarEstadoCuentas) registrador;
        final ActualizarEstadoCuenta actualizador = (ActualizarEstadoCuenta) registrador;

        final Function<EstadoCuentaForm, Optional<EstadoCuentaForm>> findEstadoCuenta =
                (EstadoCuentaForm estadoCuenta) -> {
                    final List<EstadoCuentaForm> selectAll = consultor.consultarEstadoCuentas().apply(estadoCuentaDao);

                    return selectAll.stream().filter(ec -> {
                        return
                                ec.getConcesionariaId() == estadoCuenta.getConcesionariaId() &&
                                        ec.getNroPlanConcesionaria() == estadoCuenta.getNroPlanConcesionaria();
                    }).findFirst();
                };


        final EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculo(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        registrador.registrarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        final EstadoCuentaForm ec1 = findEstadoCuenta.apply(estadoCuenta).get();
        final Timestamp tiempo1 = ec1.getFechaUltimaActualizacion();


        actualizador.actualizarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        final EstadoCuentaForm ec2 = findEstadoCuenta.apply(estadoCuenta).get();
        final Timestamp tiempo2 = ec2.getFechaUltimaActualizacion();


        assert (tiempo1.before(tiempo2));

    }
}
