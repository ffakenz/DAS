package estado_cuentas;

import ar.edu.ubp.das.mvc.db.Dao;
import estado_cuentas.boundaries.RegistrarCuota;
import estado_cuentas.boundaries.RegistrarEstadoCuenta;
import estado_cuentas.forms.CuotaForm;
import estado_cuentas.forms.EstadoCuentaForm;

import java.util.Optional;
import java.util.function.Function;

public class EstadoCuentaInteractor implements RegistrarEstadoCuenta, RegistrarCuota {
    @Override
    public Function<Dao, Optional<Long>> registrarEstadoCuenta(EstadoCuentaForm form) {
        return null;
    }

    @Override
    public Function<Dao, Optional<Long>> registrarEstadoCuenta(CuotaForm form) {
        return null;
    }
}
