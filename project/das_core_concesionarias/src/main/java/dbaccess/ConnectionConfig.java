package dbaccess;

public interface ConnectionConfig {
    default String getDriver() { return "com.microsoft.sqlserver.jdbc.SQLServerDriver"; }
    default String getDBName() { return "das_concesionarias"; }
    default String getDBUrl() { return "jdbc:sqlserver://localhost;databaseName=" + getDBName() + ";integratedSecurity=true;"; }
    default String getUsername() { return "ftestagrossa"; }
    default String getPassword() { return "93337511"; }
}
