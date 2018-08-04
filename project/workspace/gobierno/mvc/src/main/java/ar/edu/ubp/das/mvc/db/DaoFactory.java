package ar.edu.ubp.das.mvc.db;

import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    private static Properties propFile = new Properties();
    private static boolean loadProp = false;

    private DaoFactory() {
    }

    // THE MAGIC
    public static Dao getDao(final String daoName, final String daoPackage) {
        try {
            return DaoFactory.getDao(daoName, daoPackage, "default");
        } catch (final SQLException ex) { // PROBLEMAS CON EL DAO FACTORY
            ex.printStackTrace();
            return null;
        }
    }

//    public static Dao getDao(final String daoName, final String daoPackage, final DatasourceConfig config) {
//        try {
//            return DaoFactory.getDao(daoName, daoPackage, "default", config);
//        } catch (final SQLException ex) { // PROBLEMAS CON EL DAO FACTORY
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    public static Dao getDao(final String daoName, final String daoPackage, final String datasourceId, final DatasourceConfig config) throws SQLException {
//        try {
//            final String daoClassName = DaoFactory.getDaoClassName(daoName, daoPackage);
//            final DaoImpl dao = DaoImpl.class.cast(Class.forName(daoClassName).newInstance());
//            dao.setDatasource(config);
//            return dao;
//        } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
//            throw new SQLException(ex.getMessage());
//        }
//    }

    public static Dao getDao(final String daoName, final String daoPackage, final String datasourceId) throws SQLException {
        try {
            final String daoClassName = DaoFactory.getDaoClassName(daoName, daoPackage);
            final DaoImpl dao = DaoImpl.class.cast(Class.forName(daoClassName).newInstance());
            dao.setDatasource(ModuleConfigImpl.getDatasourceById(datasourceId));
            return dao;
        } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private static String getDaoClassName(final String daoName, final String daoPackage) throws SQLException {
        try {
            if (!DaoFactory.loadProp) {


                final InputStream file = DaoFactory.class.getResourceAsStream("/DaoFactory.properties");

                DaoFactory.propFile.load(file);
                file.close();

                DaoFactory.loadProp = true;
            }

            return ModuleConfigImpl.getSrcPackage() + daoPackage + ".daos." + DaoFactory.propFile.getProperty("CurrentDaoFactory") + daoName + "Dao";

        } catch (final IOException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}
