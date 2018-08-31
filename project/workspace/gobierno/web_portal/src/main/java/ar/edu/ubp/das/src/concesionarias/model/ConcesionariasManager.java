package ar.edu.ubp.das.src.concesionarias.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.daos.MSConcesionariasDao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.List;

public class ConcesionariasManager {

    MSConcesionariasDao msConcesionariasDao;

    public ConcesionariasManager(final DaoImpl msConcesionariasDao) {
        this.msConcesionariasDao = (MSConcesionariasDao) msConcesionariasDao;
    }

    public List<ConcesionariaForm> selectAll() throws SQLException {
        return msConcesionariasDao.select(null);
    }

    public List<ConcesionariaForm> selectBy(final ConcesionariaForm form) throws SQLException {
        return msConcesionariasDao.selectBy(form);
    }

    public void insert(final ConcesionariaForm concesionariaForm) throws SQLException {
        msConcesionariasDao.insert(concesionariaForm);
    }
}
