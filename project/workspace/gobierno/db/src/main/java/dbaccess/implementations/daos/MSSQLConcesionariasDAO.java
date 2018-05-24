package dbaccess.implementations.daos;

import beans.ConcesionariaForm;
import dao.DaoImpl;
import dbaccess.config.DatasourceConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSSQLConcesionariasDAO extends DaoImpl<ConcesionariaForm> {

    public MSSQLConcesionariasDAO(DatasourceConfig datasource) {
        super(datasource);
    }

    @Override
    public List<ConcesionariaForm> select(ConcesionariaForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_concesionarias", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        List<ConcesionariaForm> concesionarias  = this.executeQuery(ConcesionariaForm.class);
        this.disconnect();
        return concesionarias;
    }

    @Override
    public void insert(ConcesionariaForm form) throws SQLException {
        this.connect();
        this.setProcedure( "dbo.log_concesionaria(?, ?, ?, ?, ?, ?)" );
        this.setParameter( 1, form.getNombre());
        this.setParameter( 2, form.getConfig());
        this.setParameter( 3, form.getDireccion());
        this.setParameter( 4, form.getCuit());
        this.setParameter( 5, form.getTel());
        this.setParameter( 6, form.getEmail());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void update(ConcesionariaForm form) throws SQLException {
        this.connect();
        this.setProcedure( "dbo.aprove_concesionaria(?, ?, ?)" );
        this.setParameter( 1, form.getId());
        this.setParameter( 2, form.getFechaAlta());
        this.setParameter( 3, form.getCodigo());
        this.executeUpdate();
        this.disconnect();
    }

    @Override
    public void delete(ConcesionariaForm form) throws SQLException {

    }

    @Override
    public boolean valid(ConcesionariaForm form) throws SQLException {
        return false;
    }
}
