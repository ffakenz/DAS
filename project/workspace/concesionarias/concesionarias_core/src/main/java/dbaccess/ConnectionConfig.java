package dbaccess;

// TODO inejct this properties throught .properties file
public interface ConnectionConfig {
    default String getDBName() { return "das_concesionarias"; }
    default String getUsername() { return "rocca"; }
    default String getPassword() { return "123"; }
}
