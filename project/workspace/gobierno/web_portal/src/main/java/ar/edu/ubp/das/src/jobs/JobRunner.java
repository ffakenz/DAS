package ar.edu.ubp.das.src.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;
import java.util.stream.Collectors;


public class JobRunner implements ServletContextListener {

    private Scheduler jobScheduler;
    private List<JobObj> jobs = new LinkedList<>();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        JobBuilderFacade jobBuilder = new JobBuilderFacade();
        JobObj jobSorteo = jobBuilder.createJob("sorteo", "0/3 * * * * ?", SorteoJob.class);
        jobs.add(jobSorteo);
        JobObj jobConsumer = jobBuilder.createJob("consumer", "0/1 * * * * ?", ConsumerJob.class);
        jobs.add(jobConsumer);
        run(jobs);
    }

    public void run(List<JobObj> jobs) {
        try {
            jobScheduler = new StdSchedulerFactory().getScheduler();

            for(JobObj job : jobs) {
                jobScheduler.scheduleJob(job.getJob(), job.getTrigger());
                System.out.println(("Job " + job.getJob().getKey() + " launched"));
            }
            jobScheduler.start();
            System.out.println(("Job Scheduler started"));

        } catch (SchedulerException e) {
            System.out.println(String.format("[exception:%s]Could not start scheduler " , e.getMessage()));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            for(JobObj job : jobs) {
                jobScheduler.deleteJob(job.getJob().getKey());
                System.out.println(("Job " + job.getJob().getKey() + " deleted"));
            }

            jobScheduler.shutdown();
            System.out.println(("Job Scheduler terminated"));

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
