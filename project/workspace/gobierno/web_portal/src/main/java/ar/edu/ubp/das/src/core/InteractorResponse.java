package ar.edu.ubp.das.src.core;

import java.util.Optional;

public class InteractorResponse {
    private ResponseForward response;
    private Object result;

    public InteractorResponse(final ResponseForward response) {
        this.response = response;
        this.result = Optional.empty();
    }

    public InteractorResponse(final ResponseForward response, final Object result) {
        this.response = response;
        this.result = result;
    }

    public ResponseForward getResponse() {
        return response;
    }

    public void setResponse(final ResponseForward response) {
        this.response = response;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(final Object result) {
        this.result = result;
    }
}
