package ar.edu.ubp.das.src.jobs;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class Runner implements ServletContextListener {

    private static Scheduler schedulerSorteo;
    private static Scheduler schedulerConsumer;
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Runner.run();
    }

    public static void run() {

        JobObj jobSorteo = createJob("sorteo", "0/3 * * * * ?", SorteoJob.class);
        JobObj jobConsumer = createJob("consumer", "0/1 * * * * ?", ConsumerJob.class);

        try {

            schedulerSorteo = new StdSchedulerFactory().getScheduler();
            schedulerSorteo.scheduleJob(jobSorteo.getJob(), jobSorteo.getTrigger());
            schedulerSorteo.start();

            System.out.println(("Job sorteo launched"));

            schedulerConsumer = new StdSchedulerFactory().getScheduler();
            schedulerConsumer.scheduleJob(jobConsumer.getJob(), jobConsumer.getTrigger());
            schedulerConsumer.start();

            System.out.println(("Job consumer launched"));

        } catch (SchedulerException e) {
            System.out.println(String.format("[exception:%s]Could not start scheduler " , e.getMessage()));
        }
    }

    private static JobObj createJob(String name, String cronSchedule, Class clazz) {

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

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
