package ar.edu.ubp.das.src.concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.src.concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Utils {

    default Function<Dao, Boolean> testConcesionariaRegistrada(final ConcesionariaForm form) {
        return dao -> {
            return exists(conc -> {
                return
                        // from registrar
                        (conc.getCuit().equals(form.getCuit()) &&
                                conc.getFechaRegistracion() != null) ||
                                // from aprobar
                                conc.getId() == form.getId();
            }).apply(dao);
        };
    }


    default Function<Dao, Boolean> exists(final Predicate<ConcesionariaForm> predicate) {
        return dao -> {
            try {
                return dao.select(null).stream().anyMatch(c -> {
                    final ConcesionariaForm conc = (ConcesionariaForm) c;
                    return predicate.test(conc);
                });
            } catch (final SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }
}
