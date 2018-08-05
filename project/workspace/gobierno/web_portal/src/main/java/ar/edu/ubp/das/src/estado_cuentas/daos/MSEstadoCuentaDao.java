package ar.edu.ubp.das.src.estado_cuentas.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSEstadoCuentaDao extends DaoImpl {

    @Override
    public DynaActionForm make(final ResultSet result) throws SQLException {
        final EstadoCuentaForm estadoCuentaForm = new EstadoCuentaForm();
        estadoCuentaForm.setId(result.getLong("id"));
        estadoCuentaForm.setConcesionariaId(result.getLong("concesionaria"));
        estadoCuentaForm.setNroPlanConcesionaria(result.getLong("nro_plan_concesionaria"));
        estadoCuentaForm.setDocumentoCliente(result.getLong("documento_cliente"));
        estadoCuentaForm.setVehiculo(result.getLong("vehiculo"));
        estadoCuentaForm.setFechaAltaConcesionaria(result.getTimestamp("fecha_alta_concesionaria"));
        estadoCuentaForm.setFechaAltaSistema(result.getTimestamp("fecha_alta_sistema"));
        estadoCuentaForm.setFechaUltimaActualizacion(result.getTimestamp("fecha_ultima_actualizacion"));
        estadoCuentaForm.setEstado(result.getString("estado"));

        return estadoCuentaForm;
    }

    @Override
    public void insert(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_estado_cuentas(?, ?, ?, ?, ?, ?)");
        final EstadoCuentaForm f = new EstadoCuentaForm();
        this.setParameter(1, f.getConcesionariaId());
        this.setParameter(2, f.getNroPlanConcesionaria());
        this.setParameter(3, f.getDocumentoCliente());
        this.setParameter(4, f.getVehiculo());
        this.setParameter(5, f.getFechaAltaConcesionaria());
        this.setParameter(6, f.getEstado());

        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.update_estado_cuentas(?, ?)");
        final EstadoCuentaForm f = new EstadoCuentaForm();
        this.setParameter(1, f.getId());
        this.setParameter(2, f.getEstado());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {
    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_estado_cuentas", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<DynaActionForm> estadoCuentas = this.executeQuery();
        this.disconnect();
        return estadoCuentas;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }


}
