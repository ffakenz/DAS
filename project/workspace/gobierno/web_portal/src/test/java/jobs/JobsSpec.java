package jobs;

import ar.edu.ubp.das.src.jobs.*;
import ar.edu.ubp.das.src.jobs.runner.JobRunner;
import org.junit.Test;
import org.quartz.JobExecutionException;

public class JobsSpec {

    @Test
    public void test_job_creation() {
        JobRunner runner = new JobRunner();
        runner.contextInitialized(null);
        System.out.println("----------------------");
        runner.contextDestroyed(null);
    }

    // TESTS SORTEO
    @Test
    public void test_sorteo_base() throws JobExecutionException {
        SorteoJob sorteoJob = new SorteoJob();
        sorteoJob.execute(null);

    }
}
