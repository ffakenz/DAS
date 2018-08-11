package ar.edu.ubp.das.src.login.model.usuario;

public enum UsuarioRol {
    GOBIERNO, CONSUMER;

    public static UsuarioRol getRol(final String rol) {
        if (rol.equalsIgnoreCase("gobierno"))
            return GOBIERNO;
        return CONSUMER; // else (rol.equalsIgnoreCase("consumer"))
    }
}
