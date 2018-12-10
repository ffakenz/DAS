package ar.edu.ubp.das.src.vehiculos.daos;

import ar.edu.ubp.das.mvc.db.DaoImpl;
import ar.edu.ubp.das.src.vehiculos.forms.VehiculoForm;

import java.sql.SQLException;
import java.util.List;

public class MSVehiculoDao extends DaoImpl<VehiculoForm> {

    public MSVehiculoDao() {
        super(VehiculoForm.class);
    }

    @Override
    public void insert(final VehiculoForm form) throws SQLException {

    }

    @Override
    public void update(final VehiculoForm form) throws SQLException {

    }

    @Override
    public void delete(final VehiculoForm form) throws SQLException {

    }

    @Override
    public List<VehiculoForm> select(final VehiculoForm form) throws SQLException {
        return this.executeQueryProcedure("dbo.get_vehiculos", form);
    }

    @Override
    public boolean valid(final VehiculoForm form) throws SQLException {
        return false;
    }
}
