package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Utils {

    default Function<Dao, Boolean> testConcesionariaRegistrada(ConcesionariaForm form) {
        return dao -> {
            return exists(conc -> {
                return  conc.getCuit().equals(form.getCuit()) &&
                        conc.getFechaRegistracion() != null;
            }).apply(dao);
        };
    }


    default Function<Dao, Boolean> exists(Predicate<ConcesionariaForm> predicate) {
        return dao -> {
            try {
                return dao.select(null).stream().anyMatch( c -> {
                    ConcesionariaForm conc = (ConcesionariaForm) c;
                    return predicate.test(conc);
                });
            } catch(SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    default Function<Dao, Optional<Long>> getIdOf(ConcesionariaForm form) {
        return dao -> {
            try {
                Optional<Long> max =
                        dao.select(null).stream()
                                .filter( l -> ((ConcesionariaForm)l).getCuit().equals(form.getCuit()))
                                .map( l -> ((ConcesionariaForm) l).getId())
                                .findFirst();

                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    };
}
