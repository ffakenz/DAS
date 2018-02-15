package dbaccess;

public interface ConnectionConfig {
    default String getDBName() { return "das_concesionarias"; }
    default String getUsername() { return "ftestagrossa"; }
    default String getPassword() { return "93337511"; }
}
