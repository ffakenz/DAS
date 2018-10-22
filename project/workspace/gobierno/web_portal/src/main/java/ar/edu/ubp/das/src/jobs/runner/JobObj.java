package ar.edu.ubp.das.src.jobs.runner;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class JobObj {

    private JobDetail job;
    private Trigger trigger;

    public JobObj(JobDetail job, Trigger trigger) {
        this.job = job;
        this.trigger = trigger;
    }

    public JobDetail getJob() {
        return job;
    }

    public void setJob(JobDetail job) {
        this.job = job;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
