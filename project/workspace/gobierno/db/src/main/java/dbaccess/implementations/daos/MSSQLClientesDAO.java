package dbaccess.implementations.daos;

import beans.ClienteForm;
import beans.ConcesionariaForm;
import dao.DaoImpl;
import dbaccess.config.DatasourceConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSSQLClientesDAO extends DaoImpl<ClienteForm> {
    public MSSQLClientesDAO(DatasourceConfig datasource) {
        super(datasource);
    }

    @Override
    public List<ClienteForm> select(ClienteForm form) throws SQLException {
        this.connect();
        this.setProcedure("dbo.get_clientes", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        List<ClienteForm> clientes  = this.executeQuery(ClienteForm.class);
        this.disconnect();
        return clientes;
    }

    @Override
    public void insert(ClienteForm form) throws SQLException {

    }

    @Override
    public void update(ClienteForm form) throws SQLException {

    }

    @Override
    public void delete(ClienteForm form) throws SQLException {

    }

    @Override
    public boolean valid(ClienteForm form) throws SQLException {
        return false;
    }
}
