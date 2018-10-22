package ar.edu.ubp.das.src.jobs.runner;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.*;

public class JobBuilderFacade {

    public JobBuilderFacade() {
    }

    public JobObj createJob(String name, String cronSchedule, Class clazz) {

        JobDetail job = JobBuilder
                .newJob(clazz)
                .withIdentity(name, name + "-group")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name+"-trigger", name+"-group")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronSchedule))
                .build();

        return new JobObj(job, trigger);
    }
}
