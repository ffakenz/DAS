package estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.EstadoCuentaForm;

import java.util.Optional;
import java.util.function.Function;

public interface RegistrarEstadoCuenta {
    Function<Dao, Optional<Long>> registrarEstadoCuenta(EstadoCuentaForm form);
}
