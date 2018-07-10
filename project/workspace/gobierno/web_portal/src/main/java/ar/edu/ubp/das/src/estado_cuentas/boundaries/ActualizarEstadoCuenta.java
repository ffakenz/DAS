package ar.edu.ubp.das.src.estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;

import java.util.function.Function;

public interface ActualizarEstadoCuenta {
    Function<Dao, Boolean> actualizarEstadoCuenta(EstadoCuentaForm estadoCuenta);
}
