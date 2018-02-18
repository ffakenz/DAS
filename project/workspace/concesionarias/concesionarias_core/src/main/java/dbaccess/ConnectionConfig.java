package dbaccess;

// TODO inejct this properties throught .properties file
public interface ConnectionConfig {
    default String getDBName() { return "das_concesionarias"; }
    default String getUsername() { return "ftestagrossa"; }
    default String getPassword() { return "93337511"; }
}
