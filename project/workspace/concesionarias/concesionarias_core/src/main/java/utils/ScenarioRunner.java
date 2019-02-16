package utils;

import beans.NotificationUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.customer_types.CustomerWithDelay;
import utils.customer_types.DefaultCustomer;
import utils.customer_types.IdealCustomer;
import utils.customer_types.TypeOfCustomer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ScenarioRunner {
    private static final Logger log = LoggerFactory.getLogger(ScenarioRunner.class);

    public void run(final ScenarioBuilder builder) {

        final int cantClientes = 10;
        try {
            for (int i = 0; i < cantClientes; i ++) {
                final NotificationUpdate bean = nextUpdate();
                final TypeOfCustomer typeOfCustomer = nextTypeOfCustomer();
                log.info("Creating plan {} with typeOfCustomer = {}", bean, typeOfCustomer);
                builder.insertNotificationUpdate(bean);
                for (long nroCuota = 0; nroCuota < 60; nroCuota++) {
                    if(!typeOfCustomer.willPay(nroCuota)) break;
                    bean.setCuotaNroCuota(nroCuota);
                    builder.pagarCuotas(bean);
                    // ideal should finish the plan with type -> pagado
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private TypeOfCustomer nextTypeOfCustomer() {
        final int delay = Math.max(0, new Random().nextInt(15));
        final List<TypeOfCustomer> cs = Arrays.asList(
                new DefaultCustomer(),
                new CustomerWithDelay(delay),
                new IdealCustomer()
        );
        final int idx = Math.max(0, new Random().nextInt(cs.size()));
        return cs.get(idx);
    }
    private NotificationUpdate nextUpdate() {
        final NotificationUpdate update = new NotificationUpdate();
        update.setClienteDocumento(nextDocumento());
        update.setClienteNombre(nextNombre());
        update.setClienteApellido(nextApellido());
        update.setClienteNroTelefono(nextPhone());
        update.setClienteEmail("franco.testagrossa@gmail.com");
        // play with this in order to define the dates from which the planes exists from
        final long randomDays = Math.max(-15, new Random().nextInt(15));
        final long randomMonths = Math.max(-12, new Random().nextInt(12));
        final ZonedDateTime zonedDateTime =
                ZonedDateTime.now()
                        .plusDays(randomDays)
                        .plusMonths(randomMonths)
                        .minusYears(2);
        update.setPlanFechaAlta(Timestamp.valueOf(zonedDateTime.toLocalDateTime()));
        update.setVehiculoId(nextVehiculo());
        return update;
    }
    private Long nextVehiculo() {
        final List<Long> vehiculos = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        final int idx = Math.max(0, new Random().nextInt(vehiculos.size()));
        return vehiculos.get(idx);
    }
    private Long nextDocumento() {
        return (long) Math.abs(new Random().nextInt(99999999));
    }
    private String nextPhone() {
        final String number = String.valueOf(Math.abs(new Random().nextInt(5999999)));
        return "351" + number;
    }
    private String nextValueIn(final String fileName) {
        final String content = MockUtils.readMockBodyFromFile(fileName);
        final String[] words = content.split(",");
        final int idx = Math.max(0, new Random().nextInt(words.length));
        return words[idx];
    }
    private String nextNombre() {
        return nextValueIn("others/nombres.txt");
    }
    private String nextApellido() {
        return nextValueIn("others/apellidos.txt");
    }
}
