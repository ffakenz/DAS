package estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.CuotaForm;

import java.util.Optional;
import java.util.function.Function;

public interface RegistrarCuota {
    Function<Dao, Optional<Long>> registrarCuota(CuotaForm form);
}
