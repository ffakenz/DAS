package ar.edu.ubp.das.src.jobs.consumoo.forms;

public enum EstadoConsumo {
    SUCCESS("success"), FAILURE("failure");

    private String tipo;

    EstadoConsumo(final String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
}