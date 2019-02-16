package scenarios;

import beans.NotificationUpdate;
import dbaccess.config.DatasourceConfig;
import dbaccess.config.ModuleConfigImpl;
import utils.MockUtils;
import utils.ScenarioBuilder;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ScenarioRunner {
    public static void main(final String[] args) {
        ModuleConfigImpl.load(ScenarioRunner.class.getClassLoader());
        final DatasourceConfig config = ModuleConfigImpl.getDefaultDatasource();
        final ScenarioBuilder builder = ScenarioBuilder.getInstance(config);

        final List<NotificationUpdate> beans = Arrays.asList(
                // nextUpdate(),
                // nextUpdate(),
                // nextUpdate(),
                nextUpdate()
        );

        try {
            for (final NotificationUpdate bean : beans) {
                builder.insertNotificationUpdate(bean);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private static NotificationUpdate nextUpdate() {
        final NotificationUpdate update = new NotificationUpdate();
        update.setClienteDocumento(nextDocumento());
        update.setClienteNombre(nextNombre());
        update.setClienteApellido(nextApellido());
        update.setClienteNroTelefono(nextPhone());
        update.setClienteEmail("franco.testagrossa@gmail.com");
        update.setPlanFechaAlta(Timestamp.from(Instant.now()));
        update.setVehiculoId(nextVehiculo());
        return update;
    }

    private static Long nextVehiculo() {
        final List<Long> vehiculos = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        final int idx = Math.max(0, new Random().nextInt(vehiculos.size()));
        return vehiculos.get(idx);
    }
    private static Long nextDocumento() {
        return (long) Math.abs(new Random().nextInt(99999999));
    }

    private static String nextPhone() {
        final String number = String.valueOf(Math.abs(new Random().nextInt(5999999)));
        return "351" + number;
    }
    private static String nextValueIn(final String fileName) {
        final String content = MockUtils.readMockBodyFromFile(fileName);
        final String[] words = content.split(",");
        final int idx = Math.max(0, new Random().nextInt(words.length));
        return words[idx];
    }
    private static String nextNombre() {
        return nextValueIn("others/nombres.txt");
    }
    private static String nextApellido() {
        return nextValueIn("others/apellidos.txt");
    }
}
