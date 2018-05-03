package boundaries.estado_cuentas;

import ar.edu.ubp.das.mvc.db.Dao;
import estado_cuentas.EstadoCuentaInteractor;
import estado_cuentas.boundaries.ActualizarEstadoCuenta;
import estado_cuentas.boundaries.ConsultarEstadoCuentas;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import estado_cuentas.forms.EstadoCuentaForm;
import junit.extensions.TestSetup;
import mocks.MSEstadoCuentaDaoMock;
import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.assertThat;

public class ActualizarEstadoCuentaTest {





    @Test
    public void validateActualizarEstadoCuentaSuccessfully() {

        Dao estadoCuentaDao = new MSEstadoCuentaDaoMock();
        RegistrarEstadoCuenta registrador = new EstadoCuentaInteractor();
        ConsultarEstadoCuentas consultor = (ConsultarEstadoCuentas) registrador;
        ActualizarEstadoCuenta actualizador = (ActualizarEstadoCuenta ) registrador;

        Function<EstadoCuentaForm, Optional<EstadoCuentaForm>> findEstadoCuenta =
                (EstadoCuentaForm estadoCuenta) ->{
                    List<EstadoCuentaForm> selectAll = consultor.consultarEstadoCuentas().apply(estadoCuentaDao);

                    return selectAll.stream().filter( ec -> {
                        return
                                ec.getConcesionariaId() == estadoCuenta.getConcesionariaId() &&
                                        ec.getNroPlanConcesionaria() == estadoCuenta.getNroPlanConcesionaria();
                    }).findFirst();
                };


        EstadoCuentaForm estadoCuenta = new EstadoCuentaForm();
        estadoCuenta.setConcesionariaId(Long.valueOf(1));
        estadoCuenta.setNroPlanConcesionaria(Long.valueOf(1));
        estadoCuenta.setDocumentoCliente(Long.valueOf(93337511));
        estadoCuenta.setVehiculoId(Long.valueOf(1));
        estadoCuenta.setFechaAltaConcesionaria(Timestamp.valueOf("2018-01-01 21:58:01"));

        registrador.registrarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        EstadoCuentaForm ec1 = findEstadoCuenta.apply(estadoCuenta).get();
        Timestamp tiempo1 = ec1.getFechaUltimaActualizacion();


        actualizador.actualizarEstadoCuenta(estadoCuenta).apply(estadoCuentaDao);

        EstadoCuentaForm ec2 = findEstadoCuenta.apply(estadoCuenta).get();
        Timestamp tiempo2 = ec2.getFechaUltimaActualizacion();


        assert(tiempo1.before(tiempo2));

    }
}
