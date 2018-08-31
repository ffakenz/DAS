package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSConcesionariasDao extends DaoImpl<ConcesionariaForm> {

    public MSConcesionariasDao() {
        super(ConcesionariaForm.class);
    }

    @Override
    public void insert(final ConcesionariaForm f) throws SQLException {

        this.executeProcedure("dbo.log_concesionaria(?, ?, ?, ?, ?, ?)", f,
                "nombre", "config", "direccion", "cuit", "tel", "email");
    }

    @Override
    public void update(final ConcesionariaForm f) throws SQLException {

    }

    @Override
    public void delete(final ConcesionariaForm form) throws SQLException {

    }

    @Override
    public List<ConcesionariaForm> select(final ConcesionariaForm form) throws SQLException {

        return this.executeQueryProcedure("dbo.get_concesionarias");
    }

    @Override
    public boolean valid(final ConcesionariaForm form) throws SQLException {
        return false;
    }

    public Optional<ConcesionariaForm> selectByCodigo(final ConcesionariaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionaria_by_codigo(?)", form, "codigo")
                .stream()
                .findFirst();
    }

    public List<ConcesionariaForm> selectAprobadas() throws SQLException {
        return this.executeQueryProcedure("dbo.get_aprobadas");
    }

    public void approveConcesionaria(final ConcesionariaForm f) throws SQLException {
        this.executeProcedure("dbo.approve_concesionaria(?, ?)", f, "id", "codigo");
    }


}
