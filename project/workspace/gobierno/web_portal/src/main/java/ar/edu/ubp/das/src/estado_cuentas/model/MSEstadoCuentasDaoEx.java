package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.core.DaoExtender;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;

import java.sql.SQLException;
import java.util.Optional;

public class MSEstadoCuentasDaoEx extends DaoExtender<EstadoCuentasForm> {

    public MSEstadoCuentasDaoEx(final DaoImpl dao) {
        super(dao, EstadoCuentasForm.class);
    }

    public Optional<EstadoCuentasForm> selectByNroPlanAndConcesionaria(final EstadoCuentasForm form) throws SQLException {
        return dao.executeQueryProcedure("dbo.get_estado_cuentas_by_nro_plan_and_concesionaria(?, ?)",
                form, "concesionariaId", "nroPlanConcesionaria")
                .stream()
                .findFirst();
    }

}
