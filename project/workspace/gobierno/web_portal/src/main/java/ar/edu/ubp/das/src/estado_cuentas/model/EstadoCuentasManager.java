package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;

import java.sql.SQLException;
import java.util.Optional;

public class EstadoCuentasManager {

    MSEstadoCuentasDaoEx msEstadoCuentasDaoEx;

    public EstadoCuentasManager(final DaoImpl msEstadoCuentasDaoEx) {
        this.msEstadoCuentasDaoEx = new MSEstadoCuentasDaoEx(msEstadoCuentasDaoEx);
    }

    public Optional<EstadoCuentasForm> selectByNroPlanAndConcesionaria(final EstadoCuentasForm form) throws SQLException {
        return msEstadoCuentasDaoEx.selectByNroPlanAndConcesionaria(form);
    }

    public void insert(final EstadoCuentasForm estadoCuentasFrom) throws SQLException {
        msEstadoCuentasDaoEx.insert(estadoCuentasFrom);
    }

    public void update(final EstadoCuentasForm estadoCuentasFrom) throws SQLException {
        msEstadoCuentasDaoEx.update(estadoCuentasFrom);
    }
}
