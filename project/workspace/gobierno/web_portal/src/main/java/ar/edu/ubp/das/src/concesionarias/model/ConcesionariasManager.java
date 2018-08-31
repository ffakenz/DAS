package ar.edu.ubp.das.src.concesionarias.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.List;

public class ConcesionariasManager {

    MSConcesionariasDaoEx msConcesionariasDaoEx;

    public ConcesionariasManager(final DaoImpl msConcesionariasDaoEx) {
        this.msConcesionariasDaoEx = new MSConcesionariasDaoEx(msConcesionariasDaoEx);
    }

    public List<ConcesionariaForm> selectAll() throws SQLException {
        return msConcesionariasDaoEx.select(null);
    }

    public List<ConcesionariaForm> selectBy(final ConcesionariaForm form) throws SQLException {
        return msConcesionariasDaoEx.selectBy(form);
    }

    public void insert(final ConcesionariaForm concesionariaForm) throws SQLException {
        msConcesionariasDaoEx.insert(concesionariaForm);
    }
}
