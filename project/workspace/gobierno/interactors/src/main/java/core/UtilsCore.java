package core;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.db.Dao;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface UtilsCore {

    default Function<Dao, Optional<Long>> getIdOf(final Predicate<DynaActionForm> predicate) {
        return dao -> {
            try {
                final Optional<Long> max =
                        dao.select(null).stream()
                                .filter(l -> predicate.test(l))
                                .map(l -> Long.valueOf(l.getItem("id").orElse("1")))
                                .max(Long::compareTo);

                return max;
            } catch (final SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    }
}
