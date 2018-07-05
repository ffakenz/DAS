package estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.EstadoCuentaForm;

import java.util.function.Function;

public interface ActualizarEstadoCuenta {
    Function<Dao, Boolean> actualizarEstadoCuenta(EstadoCuentaForm estadoCuenta);
}
