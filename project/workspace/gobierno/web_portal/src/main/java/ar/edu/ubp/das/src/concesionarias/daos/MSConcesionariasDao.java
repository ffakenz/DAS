package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConcesionariasDao extends DaoImpl<ConcesionariaForm> {

    public MSConcesionariasDao() {
        super(ConcesionariaForm.class);
    }

    public ConcesionariaForm make(final ResultSet result) throws SQLException {
        final ConcesionariaForm concesionariaForm = new ConcesionariaForm();
        concesionariaForm.setId(result.getLong("id"));
        concesionariaForm.setNombre(result.getString("nombre"));
        concesionariaForm.setConfig(result.getString("config"));
        concesionariaForm.setFechaRegistracion(result.getTimestamp("fecha_registracion"));
        concesionariaForm.setFechaAlta(result.getTimestamp("fecha_alta"));
        concesionariaForm.setCodigo(result.getString("codigo"));
        concesionariaForm.setDireccion(result.getString("direccion"));
        concesionariaForm.setCuit(result.getString("cuit"));
        concesionariaForm.setTel(result.getString("tel"));
        concesionariaForm.setEmail(result.getString("email"));

        return concesionariaForm;
    }

    @Override
    public void insert(final ConcesionariaForm f) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_concesionaria(?, ?, ?, ?, ?, ?)");
        this.setParameter(1, f.getNombre());
        this.setParameter(2, f.getConfig());
        this.setParameter(3, f.getDireccion());
        this.setParameter(4, f.getCuit());
        this.setParameter(5, f.getTel());
        this.setParameter(6, f.getEmail());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(final ConcesionariaForm f) throws SQLException {
        this.connect();
        this.setProcedure("dbo.aprove_concesionaria(?, ?, ?)");
        this.setParameter(1, f.getId());
        this.setParameter(2, f.getFechaAlta());
        this.setParameter(3, f.getCodigo());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final ConcesionariaForm form) throws SQLException {

    }

    @Override
    public List<ConcesionariaForm> select(final ConcesionariaForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<ConcesionariaForm> concesionarias = this.executeQuery();
        this.disconnect();
        return concesionarias;
    }

    @Override
    public boolean valid(final ConcesionariaForm form) throws SQLException {
        return false;
    }


    public List<ConcesionariaForm> selectBy(final ConcesionariaForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.",
                form, "");
    }
}
