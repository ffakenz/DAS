package ar.edu.ubp.das.src.core;

import java.util.Optional;

public class InteractorResponse<T> {
    private ResponseForward response;
    private Optional<T> result;


    public InteractorResponse() {
    }

    public InteractorResponse(final ResponseForward response) {
        this.response = response;
        this.result = Optional.empty();
    }

    public InteractorResponse(final ResponseForward response, final Optional<T> result) {
        this.response = response;
        this.result = result;
    }

    public ResponseForward getResponse() {
        return response;
    }

    public void setResponse(final ResponseForward response) {
        this.response = response;
    }

    public Optional<T> getResult() {
        return result;
    }

    public void setResult(final Optional<T> result) {
        this.result = result;
    }
}
