package ar.edu.ubp.das.src.jobs.sorteo.forms;

public enum EstadoSorteo {
    NUEVO("nuevo"),
    PENDIENTE("pendiente"),
    EN_EJECUCION("en_ejecucion"),
    COMPLETADO("completado");

    private String tipo;

    EstadoSorteo(final String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
}