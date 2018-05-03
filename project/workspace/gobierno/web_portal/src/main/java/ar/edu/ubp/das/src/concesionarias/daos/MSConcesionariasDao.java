package ar.edu.ubp.das.src.concesionarias.daos;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoImpl;
import concesionarias.forms.ConcesionariaForm;
import login.forms.LogInForm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MSConcesionariasDao extends DaoImpl {

    @Override
    public DynaActionForm make(ResultSet result) throws SQLException {
        ConcesionariaForm concesionariaForm = new ConcesionariaForm();
        concesionariaForm.setId(result.getLong("id"));
        concesionariaForm.setNombre(result.getString("nombre"));
        concesionariaForm.setConfig(result.getString("config"));
        concesionariaForm.setFechaRegistracion(result.getDate("fecha_registracion"));
        concesionariaForm.setFechaAlta(result.getDate("fecha_alta"));
        concesionariaForm.setCodigo(result.getString("codigo"));
        concesionariaForm.setDireccion(result.getString("direccion"));
        concesionariaForm.setCuit(result.getString("cuit"));
        concesionariaForm.setTel(result.getString("tel"));
        concesionariaForm.setEmail(result.getString("email"));

        return concesionariaForm;
    }

    @Override
    public void insert(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure( "dbo.log_concesionaria(?, ?, ?, ?, ?, ?)" );
        ConcesionariaForm f = (ConcesionariaForm) form;
        this.setParameter( 1, f.getNombre());
        this.setParameter( 2, f.getConfig());
        this.setParameter( 3, f.getDireccion());
        this.setParameter( 4, f.getCuit());
        this.setParameter( 5, f.getTel());
        this.setParameter( 6, f.getEmail());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure( "dbo.aprove_concesionaria(?, ?, ?)" );
        ConcesionariaForm f = ((ConcesionariaForm) form);
        this.setParameter( 1, f.getId());
        this.setParameter( 2, f.getFechaAlta());
        this.setParameter( 3, f.getCodigo());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(DynaActionForm form) throws SQLException {

    }

    @Override
    public List<DynaActionForm> select(DynaActionForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        List<DynaActionForm> concesionarias  = this.executeQuery();
        this.disconnect();
        return concesionarias;
    }

    @Override
    public boolean valid(DynaActionForm form) throws SQLException {
        return false;
    }
}
