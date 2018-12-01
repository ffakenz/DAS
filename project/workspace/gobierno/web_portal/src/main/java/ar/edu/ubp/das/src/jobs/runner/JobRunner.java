package ar.edu.ubp.das.src.jobs.runner;

import ar.edu.ubp.das.src.jobs.ConsumerJob;
import ar.edu.ubp.das.src.jobs.sorteo.SorteoJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;


public class JobRunner implements ServletContextListener {

    private Scheduler jobScheduler;
    private List<JobObj> jobs = new LinkedList<>();

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {

        final JobBuilderFacade jobBuilder = new JobBuilderFacade();

        final JobObj jobSorteo = jobBuilder.createJob("sorteo", "0/3 * * * * ?", SorteoJob.class);
        jobs.add(jobSorteo);

        final JobObj jobConsumer = jobBuilder.createJob("consumer", "0/1 * * * * ?", ConsumerJob.class);
        jobs.add(jobConsumer);

        run(jobs);
    }

    public void run(final List<JobObj> jobs) {
        try {
            jobScheduler = new StdSchedulerFactory().getScheduler();

            for (final JobObj job : jobs) {
                jobScheduler.scheduleJob(job.getJob(), job.getTrigger());
                System.out.println(("Job " + job.getJob().getKey() + " launched"));
            }

            jobScheduler.start();

            System.out.println(("Job Scheduler started"));

        } catch (final SchedulerException e) {
            System.out.println(String.format("[exception:%s]Could not start scheduler ", e.getMessage()));
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        try {
            for (final JobObj job : jobs) {
                jobScheduler.deleteJob(job.getJob().getKey());
                System.out.println(("Job " + job.getJob().getKey() + " deleted"));
            }

            jobScheduler.shutdown();
            System.out.println(("Job Scheduler terminated"));

        } catch (final SchedulerException e) {
            e.printStackTrace();
        }
    }
}
