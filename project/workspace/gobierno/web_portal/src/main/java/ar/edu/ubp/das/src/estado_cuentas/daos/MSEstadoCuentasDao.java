package ar.edu.ubp.das.src.estado_cuentas.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSEstadoCuentasDao extends DaoImpl<EstadoCuentasForm> {


    public MSEstadoCuentasDao() {
        super(EstadoCuentasForm.class);
    }

    @Override
    public void insert(final EstadoCuentasForm bean) throws SQLException {
        this.executeProcedure("dbo.log_estado_cuentas(?, ?, ?, ?, ?, ?)", bean,
                "concesionariaId", "nroPlanConcesionaria", "documentoConsumer",
                "vehiculo", "fechaAltaConcesionaria", "estado");
    }

    @Override
    public void update(final EstadoCuentasForm bean) throws SQLException {
        this.executeProcedure("dbo.update_estado_cuentas(?, ?, ?)", bean,
                "concesionariaId", "nroPlanConcesionaria", "estado");
    }

    @Override
    public void delete(final EstadoCuentasForm form) throws SQLException {
    }

    @Override
    public List<EstadoCuentasForm> select(final EstadoCuentasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_estado_cuentas");
    }

    @Override
    public boolean valid(final EstadoCuentasForm form) throws SQLException {
        return false;
    }

    public Optional<EstadoCuentasForm> selectByNroPlanAndConcesionaria(final EstadoCuentasForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_estado_cuentas_by_nro_plan_and_concesionaria(?, ?)",
                form, "concesionariaId", "nroPlanConcesionaria")
                .stream()
                .findFirst();
    }

    public Optional<EstadoCuentasForm> selectEstadoCuentasById(final Long id) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_estado_cuentas_by_id(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, id);
        final List<EstadoCuentasForm> result = this.executeQuery();
        this.disconnect();
        return result
                .stream()
                .findFirst();
    }
}
