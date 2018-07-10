package ar.edu.ubp.das.src.estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.estado_cuentas.forms.CuotaForm;

import java.util.Optional;
import java.util.function.Function;

public interface RegistrarCuota {
    Function<Dao, Optional<Long>> registrarCuota(CuotaForm form);
}
