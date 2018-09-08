package ar.edu.ubp.das.src.core;

public class InteractorResponse<T> {
    private ResponseForward response;
    private T result;


    public InteractorResponse() {
    }

    public InteractorResponse(final ResponseForward response) {
        this.response = response;
    }

    public InteractorResponse(final ResponseForward response, final T result) {
        this.response = response;
        this.result = result;
    }

    public ResponseForward getResponse() {
        return response;
    }

    public void setResponse(final ResponseForward response) {
        this.response = response;
    }

    public T getResult() {
        return result;
    }

    public void setResult(final T result) {
        this.result = result;
    }
}
