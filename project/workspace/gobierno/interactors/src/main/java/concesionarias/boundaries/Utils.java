package concesionarias.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import concesionarias.forms.ConcesionariaForm;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public interface Utils {
    default Function<Dao, Boolean> exists(ConcesionariaForm form) {
        return dao -> {
            try {
                return dao.select(null).stream().anyMatch( c -> {
                    ConcesionariaForm conc = (ConcesionariaForm) c;
                    return
                            conc.getNombre().equals(form.getNombre()) &&
                                    conc.getConfig().equals(form.getConfig()) &&
                                    conc.getId() == form.getId();
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
                                .filter( l ->
                                        ((ConcesionariaForm)l).getNombre() == form.getNombre() &&
                                                ((ConcesionariaForm)l).getConfig() == form.getConfig()
                                )
                                .map( l -> ((ConcesionariaForm) l).getId())
                                .max(Comparable::compareTo);

                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    };
}
