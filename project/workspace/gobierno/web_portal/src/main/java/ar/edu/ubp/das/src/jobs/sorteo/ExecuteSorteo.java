package ar.edu.ubp.das.src.jobs.sorteo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import clients.factory.ClientFactory;

public class ExecuteSorteo {

    public static void main(final String[] args) {

        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        // Instantiate a manager for sorteo executions
        SorteoJob sorteoJob = new SorteoJob(dataSourceConfig, ClientFactory.getInstance(), new SendEmail());

        sorteoJob.createSorteo();
        sorteoJob.execute();

        sorteoJob.getLastSorteo().ifPresent(System.out::println);
    }

}
