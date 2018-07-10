package ar.edu.ubp.das.src.core;

public class  InteractorResponse {
    protected ResponseForward response;
    protected Object result;


    public ResponseForward getResponse() {
        return response;
    }

    public void setResponse(ResponseForward response) {
        this.response = response;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
