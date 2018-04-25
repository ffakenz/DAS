package estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import estado_cuentas.forms.EstadoCuentaForm;

import java.util.Optional;
import java.util.function.Function;

public interface ActualizarEstadoCuenta {
    Function<Dao, Boolean> actualizarEstadoCuenta(EstadoCuentaForm estadoCuenta);
}
