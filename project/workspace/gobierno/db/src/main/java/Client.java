import beans.ClienteForm;
import beans.ConcesionariaForm;
import beans.EstadoCuentaForm;
import dao.Dao;
import dbaccess.core.DAOAbstractFactory;
import dbaccess.core.DAOFactory;
import dbaccess.config.DatasourceType;
import dbaccess.implementations.daos.DaoType;

import java.sql.SQLException;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        // create mssql factory
        DAOAbstractFactory mssqlFactory = DAOAbstractFactory.getDAOFactory(DAOFactory.MSSQL, DatasourceType.DEFAULT);

        // get dao for concesionarias
        Dao concesionariasDao = mssqlFactory.getDao(DaoType.CONCESIONARIAS);
        Dao clientesDao = mssqlFactory.getDao(DaoType.CLIENTES);
        Dao estadoCuentasDao = mssqlFactory.getDao(DaoType.ESTADO_CUENTAS);

        try {
            System.out.println("CONCESIONARIAS");
            List<ConcesionariaForm> concesionarias = concesionariasDao.select(null);
            concesionarias.forEach(System.out::println);

            System.out.println("CLIENTES");
            List<ClienteForm> clientes = clientesDao.select(null);
            clientes.forEach(System.out::println);

            System.out.println("ESTADO DE CUENTAS");
            List<EstadoCuentaForm> estadoDeCuentas = estadoCuentasDao.select(null);
            estadoDeCuentas.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
