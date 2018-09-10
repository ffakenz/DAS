package ar.edu.ubp.das.src.usuarios.model;

public enum UsuarioRol {
    GOBIERNO("gobierno"), CONSUMER("consumer");

    private final String text;

    UsuarioRol(final String text) {
        this.text = text;
    }

    public static UsuarioRol getRol(final String rol) {
        if (rol.equalsIgnoreCase("gobierno"))
            return GOBIERNO;
        return CONSUMER; // else (rol.equalsIgnoreCase("consumer"))
    }

    @Override
    public String toString() {
        return text;
    }

}
