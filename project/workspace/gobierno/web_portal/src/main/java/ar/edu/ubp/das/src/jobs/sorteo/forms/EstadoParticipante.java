package ar.edu.ubp.das.src.jobs.sorteo.forms;

public enum EstadoParticipante {
    PARTICIPANTE("participante"),
    GANADOR("ganador"),
    CANCELADO("cancelado"),
    PENDIENTE_CANCELACION("pendiente_cancelacion");

    private String tipo;

    EstadoParticipante(final String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
}