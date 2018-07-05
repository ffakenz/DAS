package estado_cuentas;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.CuotaForm;
import beans.EstadoCuentaForm;
import core.UtilsCore;
import estado_cuentas.boundaries.ActualizarEstadoCuenta;
import estado_cuentas.boundaries.ConsultarEstadoCuentas;
import estado_cuentas.boundaries.RegistrarCuota;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EstadoCuentaInteractor implements
        RegistrarEstadoCuenta
        , RegistrarCuota
        , ConsultarEstadoCuentas
        , ActualizarEstadoCuenta, UtilsCore {

    @Override
    public Function<Dao, Optional<Long>> registrarEstadoCuenta(final EstadoCuentaForm form) {
        return dao -> {
            try {
                dao.insert(form);
            } catch (final SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
            return getIdOf(d -> {
                EstadoCuentaForm ec = (EstadoCuentaForm) d;
                ec.setItem("id", ec.getId().toString());
                return
                        ec.getConcesionariaId() == form.getConcesionariaId() &&
                                ec.getNroPlanConcesionaria() == form.getNroPlanConcesionaria();
            }).apply(dao);
        };
    }

    @Override
    public Function<Dao, Optional<Long>> registrarCuota(final CuotaForm form) {
        return dao -> {
            try {
                dao.insert(form);
            } catch (final SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
            return getIdOf(d -> {
                CuotaForm c = (CuotaForm) d;
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
            } catch (final SQLException ex) {
                ex.printStackTrace();
                return new ArrayList<>();
            }
        };
    }

    @Override
    public Function<Dao, Boolean> actualizarEstadoCuenta(final EstadoCuentaForm estadoCuenta) {
        return dao -> {
            try {

                final Boolean existsEstadoCuenta =
                        dao.select(null).stream()
                                .anyMatch(dynaActionForm -> {
                                    final EstadoCuentaForm estadoCuentaForm = (EstadoCuentaForm) dynaActionForm;
                                    return
                                            estadoCuentaForm.getConcesionariaId() == estadoCuenta.getConcesionariaId() &&
                                                    estadoCuentaForm.getNroPlanConcesionaria() == estadoCuenta.getNroPlanConcesionaria();
                                });

                if (existsEstadoCuenta) {

                    estadoCuenta.setFechaUltimaActualizacion(Timestamp.from(Instant.now()));
                    dao.update(estadoCuenta);
                }

                return existsEstadoCuenta;
            } catch (final SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        };
    }
}
