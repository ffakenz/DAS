package ar.edu.ubp.das.src.core;

public enum ResponseForward {
    SUCCESS("success"),
    WARNING("warning"),
    FAILURE("failure");

    private final String forward;

    ResponseForward(final String forward) {
        this.forward = forward;
    }

    public String getForward() {
        return forward;
    }
}
