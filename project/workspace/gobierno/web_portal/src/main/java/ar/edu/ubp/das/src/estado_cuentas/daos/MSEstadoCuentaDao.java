package ar.edu.ubp.das.src.estado_cuentas.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;

import java.sql.SQLException;
import java.util.List;

public class MSEstadoCuentaDao extends DaoImpl<EstadoCuentaForm> {


    public MSEstadoCuentaDao() {
        super(EstadoCuentaForm.class);
    }

    @Override
    public void insert(final EstadoCuentaForm bean) throws SQLException {
        this.executeProcedure("dbo.log_estado_cuentas(?, ?, ?, ?, ?, ?)", bean,
                "concesionariaId", "nroPlanConcesionaria", "documentoConsumer",
                "vehiculo", "fechaUltimaActualizacion", "estado");
    }

    @Override
    public void update(final EstadoCuentaForm bean) throws SQLException {
        this.executeProcedure("dbo.update_estado_cuentas(?, ?)", bean,
                "concesionariaId", "nroPlanConcesionaria", "estado");
    }

    @Override
    public void delete(final EstadoCuentaForm form) throws SQLException {
    }

    @Override
    public List<EstadoCuentaForm> select(final EstadoCuentaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_estado_cuentas", form);
    }

    @Override
    public boolean valid(final EstadoCuentaForm form) throws SQLException {
        return false;
    }


}
