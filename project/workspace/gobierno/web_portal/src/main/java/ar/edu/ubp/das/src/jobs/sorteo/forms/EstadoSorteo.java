package ar.edu.ubp.das.src.jobs.sorteo.forms;

public enum EstadoSorteo {
    NUEVO("nuevo"),
    PENDIENTE_CONSUMO("pendiente_consumo"),
    PENDIENTE_CANCELACION("pendiente_cancelacion"),
    PENDIENTE_SELECCION_GANADOR("pendiente_seleccion_ganador"),
    PENDIENTE_NOTIFICACION_GANADOR("pendiente_notificacion_ganador"),
    PENDIENTE_NOTIFICACION_CONCESIONARIAS("pendiente_notificacion_concesionarias"),
    COMPLETADO("completado"),
    FALLADO("fallado");

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