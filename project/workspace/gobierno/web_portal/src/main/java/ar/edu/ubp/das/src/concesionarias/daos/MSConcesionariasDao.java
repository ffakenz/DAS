package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MSConcesionariasDao extends DaoImpl<ConcesionariaForm> {

    public MSConcesionariasDao() {
        super(ConcesionariaForm.class);
    }

    @Override
    public void insert(final ConcesionariaForm f) throws SQLException {

        this.executeProcedure("dbo.log_concesionaria(?, ?, ?, ?, ?)", f,
                "nombre", "direccion", "cuit", "tel", "email");
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

    public Optional<ConcesionariaForm> selectById(final ConcesionariaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionaria_by_id(?)", form,
                "id")
                .stream()
                .findFirst();
    }

    public Optional<ConcesionariaForm> selectById(final Long id) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionaria_by_id(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, id);
        final List<ConcesionariaForm> result = this.executeQuery();
        this.disconnect();
        return result
                .stream()
                .findFirst();
    }

    public Optional<ConcesionariaForm> selectByCuit(final ConcesionariaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionaria_by_cuit(?)", form,
                "cuit")
                .stream()
                .findFirst();
    }

    public Optional<ConcesionariaForm> selectByCodigo(final ConcesionariaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_concesionaria_by_codigo(?)", form,
                "codigo")
                .stream()
                .findFirst();
    }

    public List<ConcesionariaForm> selectAprobadas() throws SQLException {
        return this.executeQueryProcedure("dbo.get_aprobadas");
    }

    public List<ConcesionariaForm> selectAprobadasDesactualizadas(final Integer days) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_aprobadas_desactualizadas(?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.setParameter(1, days);
        final List<ConcesionariaForm> result = this.executeQuery();
        this.disconnect();
        return result;
    }

    public void approveConcesionaria(final ConcesionariaForm form) throws SQLException {
        this.executeProcedure("dbo.approve_concesionaria(?, ?)", form,
                "id", "codigo");
    }

    public void disapproveConcesionaria(final ConcesionariaForm form) throws SQLException {
        this.executeProcedure("dbo.disapprove_concesionaria(?)", form,
                "id");
    }


}
