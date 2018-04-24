package estado_cuentas;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.boundaries.Utils;
import estado_cuentas.boundaries.ActualizarEstadoCuenta;
import estado_cuentas.boundaries.ConsultarEstadoCuentas;
import estado_cuentas.boundaries.RegistrarCuota;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import estado_cuentas.forms.CuotaForm;
import estado_cuentas.forms.EstadoCuentaForm;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EstadoCuentaInteractor implements
        RegistrarEstadoCuenta
        , RegistrarCuota
        , ConsultarEstadoCuentas
        , ActualizarEstadoCuenta, Utils {

    @Override
    public Function<Dao, Optional<Long>> registrarEstadoCuenta(EstadoCuentaForm form) {
        return dao -> {
            try {
                dao.insert(form);
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
            return getIdOf(d -> {
                EstadoCuentaForm ec = (EstadoCuentaForm)d;
                ec.setItem("id", ec.getId().toString());
                return
                    ec.getConcesionariaId() == form.getConcesionariaId() &&
                    ec.getNroPlanConcesionaria() == form.getNroPlanConcesionaria();
            }).apply(dao);
        };
    }

    @Override
    public Function<Dao, Optional<Long>> registrarCuota(CuotaForm form) {
        return dao -> {
            try {
                dao.insert(form);
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
            return getIdOf(d -> {
                CuotaForm c = (CuotaForm)d;
                c.setItem("id", c.getId().toString());
                return c.getEstadoCuentaId() == form.getEstadoCuentaId();
            }).apply(dao);
        };
    }

    @Override
    public Function<Dao, List<EstadoCuentaForm>> consultarEstadoCuentas() {
        return dao -> {
            try {
                return
                    dao.select(null).stream()
                        .map(d -> (EstadoCuentaForm) d)
                        .collect(Collectors.toList());
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new ArrayList<>();
            }
        };
    }

    @Override
    public Function<Dao, Boolean> actualizarEstadoCuenta(EstadoCuentaForm estadoCuenta) {
        return dao -> {
            try {

                Boolean existsEstadoCuenta =
                    dao.select(null).stream()
                        .anyMatch( dynaActionForm -> {
                           EstadoCuentaForm  estadoCuentaForm = (EstadoCuentaForm) dynaActionForm;
                           return
                           estadoCuentaForm.getConcesionariaId() == estadoCuenta.getConcesionariaId() &&
                               estadoCuentaForm.getNroPlanConcesionaria() == estadoCuenta.getNroPlanConcesionaria();
                        });

                if(existsEstadoCuenta) {

                    estadoCuenta.setFechaUltimaActualizacion(Timestamp.from(Instant.now()));
                    dao.update(estadoCuenta);
                }

                return existsEstadoCuenta;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        };
    }
}
