package dbaccess.implementations.daos;

import beans.ConcesionariaForm;
import beans.EstadoCuentaForm;
import dao.DaoImpl;
import dbaccess.config.DatasourceConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSSQLEstadoCuentasDAO  extends DaoImpl<EstadoCuentaForm> {
    public MSSQLEstadoCuentasDAO(DatasourceConfig datasource) {
        super(datasource);
    }

    @Override
    public List<EstadoCuentaForm> select(EstadoCuentaForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_estado_cuentas", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        List<EstadoCuentaForm> estadoDeCuentas  = this.executeQuery(EstadoCuentaForm.class);
        this.disconnect();
        return estadoDeCuentas;
    }

    @Override
    public void insert(EstadoCuentaForm form) throws SQLException {

    }

    @Override
    public void update(EstadoCuentaForm form) throws SQLException {

    }

    @Override
    public void delete(EstadoCuentaForm form) throws SQLException {

    }

    @Override
    public boolean valid(EstadoCuentaForm form) throws SQLException {
        return false;
    }
}
