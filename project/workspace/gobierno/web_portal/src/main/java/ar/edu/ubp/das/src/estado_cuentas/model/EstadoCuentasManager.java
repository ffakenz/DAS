package ar.edu.ubp.das.src.estado_cuentas.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.estado_cuentas.daos.MSEstadoCuentasDao;
import ar.edu.ubp.das.src.estado_cuentas.forms.EstadoCuentasForm;

import java.sql.SQLException;
import java.util.Optional;

public class EstadoCuentasManager {

    MSEstadoCuentasDao msEstadoCuentasDao;

    public EstadoCuentasManager(final DaoImpl msEstadoCuentasDao) {
        this.msEstadoCuentasDao = (MSEstadoCuentasDao) msEstadoCuentasDao;
    }

    public Optional<EstadoCuentasForm> selectByNroPlanAndConcesionaria(final EstadoCuentasForm form) throws SQLException {
        return msEstadoCuentasDao.selectByNroPlanAndConcesionaria(form);
    }

    public void insert(final EstadoCuentasForm estadoCuentasFrom) throws SQLException {
        msEstadoCuentasDao.insert(estadoCuentasFrom);
    }

    public void update(final EstadoCuentasForm estadoCuentasFrom) throws SQLException {
        msEstadoCuentasDao.update(estadoCuentasFrom);
    }
}
