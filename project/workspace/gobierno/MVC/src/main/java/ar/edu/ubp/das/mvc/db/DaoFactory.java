package ar.edu.ubp.das.mvc.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;

public class DaoFactory {

    private static Properties propFile = new Properties();
    private static boolean    loadProp = false;

    private DaoFactory() {}

    public static Dao getDao(String daoName, String daoPackage) {
        try {
            return DaoFactory.getDao(daoName, daoPackage, "default");
        } catch(SQLException ex) { // PROBLEMAS CON EL DAO FACTORY
            ex.printStackTrace();
            return null;
        }
    }

    public static Dao getDao(String daoName, String daoPackage, String datasourceId) throws SQLException {
        try {
            String daoClassName = DaoFactory.getDaoClassName(daoName, daoPackage);
        	DaoImpl dao = DaoImpl.class.cast(Class.forName(daoClassName).newInstance());
        	        dao.setDatasource(ModuleConfigImpl.getDatasourceById(datasourceId));
            return dao;            
        }
        catch(InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    private static String getDaoClassName(String daoName, String daoPackage) throws SQLException {
        try {
        	if(!DaoFactory.loadProp) {


                InputStream file = DaoFactory.class.getResourceAsStream("/DaoFactory.properties");

                DaoFactory.propFile.load(file);
                file.close();

                DaoFactory.loadProp = true;
            }

            try {
                return ModuleConfigImpl.getSrcPackage() + daoPackage + ".daos." + DaoFactory.propFile.getProperty("CurrentDaoFactory") + daoName + "Dao";
            } catch(NullPointerException e) {
        	    e.printStackTrace();
                return ModuleConfigImpl.getSrcPackage() + daoPackage + ".daos." + DaoFactory.propFile.getProperty("CurrentDaoFactory") + daoName + "Dao";
            }
        }
        catch(IOException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

}