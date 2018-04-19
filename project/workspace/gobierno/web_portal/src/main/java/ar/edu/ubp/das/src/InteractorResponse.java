package ar.edu.ubp.das.src;

import ar.edu.ubp.das.mvc.config.ForwardConfig;

public class  InteractorResponse {
    protected ForwardConfig forwardConfig;
    protected Object result;

    public ForwardConfig getForwardConfig() {
        return forwardConfig;
    }

    public void setForwardConfig(ForwardConfig forwardConfig) {
        this.forwardConfig = forwardConfig;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
