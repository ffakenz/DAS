package ar.edu.ubp.das.src.concesionarias.model;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;
import ar.edu.ubp.das.src.core.DaoExtender;

import java.sql.SQLException;
import java.util.List;

public class MSConcesionariasDaoEx extends DaoExtender<ConcesionariaForm> {

    public MSConcesionariasDaoEx(final DaoImpl dao) {
        super(dao, ConcesionariaForm.class);
    }

    public List<ConcesionariaForm> selectBy(final ConcesionariaForm form) throws SQLException {
        return dao.executeQueryProcedure("dbo.",
                form, "");
    }

}
