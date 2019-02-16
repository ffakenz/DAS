package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import clients.factory.ClientFactory;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import static ar.edu.ubp.das.src.utils.Constants.FROM_DAYS;
import static ar.edu.ubp.das.src.utils.Constants.TO_DAYS;

public class ExecuteConsumo {

    public static void main(final String[] args) {


        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        final Timestamp fechaEjecucion = Timestamp.valueOf(ZonedDateTime.now().minusDays(15).toLocalDateTime());
        ConsumoJob consumoJob = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance(), fechaEjecucion);
        // primer consumo que trae toda la info historica
        FROM_DAYS = 1000;
        TO_DAYS = 15;
        consumoJob.execute(null);


        consumoJob = new ConsumoJob(dataSourceConfig, ClientFactory.getInstance());
        // primer consumo que trae toda la info historica
        FROM_DAYS = 15;
        TO_DAYS = 0;
        consumoJob.execute(null);
    }
}
