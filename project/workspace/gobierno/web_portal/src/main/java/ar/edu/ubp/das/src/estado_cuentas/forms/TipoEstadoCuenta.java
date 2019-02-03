package ar.edu.ubp.das.src.estado_cuentas.forms;

public enum TipoEstadoCuenta {
    INICIAL("inicial"),
    EN_PROCESO("en_proceso"),
    PAGADO("pagado"),
    PENDIENTE_CANCELACION("pendiente_cancelacion"),
    CANCELADO("cancelado");

    private String tipo;

    TipoEstadoCuenta(final String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
}