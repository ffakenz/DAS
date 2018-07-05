package estado_cuentas.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.EstadoCuentaForm;

import java.util.List;
import java.util.function.Function;

public interface ConsultarEstadoCuentas {

    Function<Dao, List<EstadoCuentaForm>> consultarEstadoCuentas();
}
