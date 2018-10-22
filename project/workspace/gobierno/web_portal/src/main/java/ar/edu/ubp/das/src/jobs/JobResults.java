package ar.edu.ubp.das.src.jobs;

import org.quartz.Job;

public abstract class JobResults {
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
