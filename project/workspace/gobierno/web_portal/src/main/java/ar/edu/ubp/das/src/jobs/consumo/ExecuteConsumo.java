package ar.edu.ubp.das.src.jobs.consumo;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import clients.factory.ClientFactory;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class ExecuteConsumo {

    public static void main(final String[] args) {

        final DatasourceConfig dataSourceConfig = new DatasourceConfig();
        dataSourceConfig.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceConfig.setUrl("jdbc:sqlserver://localhost;databaseName=db_gobierno;");
        dataSourceConfig.setUsername("SA");
        dataSourceConfig.setPassword("Das12345");

        // primer consumo que trae toda la info historica
        // se ejecuta un consumo hace 15 dias atrás
        final Timestamp fechaEjecucion = Timestamp.valueOf(ZonedDateTime.now().minusDays(15).toLocalDateTime());
        new ConsumoJob(dataSourceConfig, ClientFactory.getInstance(), fechaEjecucion, -1000, -15).execute();

        // consumo que trae toda la info historica de los ultimos 15 dias
        new ConsumoJob(dataSourceConfig, ClientFactory.getInstance(), -15, 0).execute();
    }
}
