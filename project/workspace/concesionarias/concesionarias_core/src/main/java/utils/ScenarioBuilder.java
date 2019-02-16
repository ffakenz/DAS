package utils;

import beans.NotificationUpdate;
import dbaccess.config.DatasourceConfig;

import java.sql.SQLException;

public class ScenarioBuilder extends DaoImpl<Object> {
    private static ScenarioBuilder instance;

    private ScenarioBuilder(final DatasourceConfig dataSourceConfig) {
        super(Object.class);
        this.setDatasource(dataSourceConfig);
    }

    public static ScenarioBuilder getInstance(final DatasourceConfig dataSourceConfig) {
        if (instance == null) {
            instance = new ScenarioBuilder(dataSourceConfig);
        }
        return instance;
    }

    public void insertNotificationUpdate(final NotificationUpdate bean) throws SQLException {
        executeProcedure("create_plan_new_cliente(?,?,?,?,?,?,?)", bean,
                "clienteDocumento","clienteNombre","clienteApellido","clienteNroTelefono"
                , "clienteEmail", "planFechaAlta", "vehiculoId");
    }
}
