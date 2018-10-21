package jobs;

import ar.edu.ubp.das.src.jobs.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class JobsSpec {

    @Test
    public void test_job_creation() {
        JobRunner runner = new JobRunner();
        runner.contextInitialized(null);
        System.out.println("----------------------");
        runner.contextDestroyed(null);
    }
}
