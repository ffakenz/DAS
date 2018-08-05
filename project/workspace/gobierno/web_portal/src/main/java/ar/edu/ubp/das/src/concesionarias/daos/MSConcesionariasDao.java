package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSConcesionariasDao extends DaoImpl {

    @Override
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
    public void insert(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.log_concesionaria(?, ?, ?, ?, ?, ?)");
        final ConcesionariaForm f = (ConcesionariaForm) form;
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
    public void update(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.aprove_concesionaria(?, ?, ?)");
        final ConcesionariaForm f = (ConcesionariaForm) form;
        this.setParameter(1, f.getId());
        this.setParameter(2, f.getFechaAlta());
        this.setParameter(3, f.getCodigo());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(final DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(final DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        final List<DynaActionForm> concesionarias = this.executeQuery();
        this.disconnect();
        return concesionarias;
    }

    @Override
    public boolean valid(final DynaActionForm form) throws SQLException {
        return false;
    }
}
