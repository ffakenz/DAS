package clientes.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import clientes.forms.ClienteForm;
import clientes.forms.ClienteForm;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Utils {

    default Function<Dao, Boolean> testClienteRegistrado(ClienteForm form) {
        return dao -> exists( cliente ->
                    cliente.getDocumento().equals(form.getDocumento()) &&
                    cliente.getConcesionaria().equals(form.getConcesionaria())
        ).apply(dao);
    }


    default Function<Dao, Boolean> exists(Predicate<ClienteForm> predicate) {
        return dao -> {
            try {
                return dao.select(null).stream().anyMatch( c -> {
                    ClienteForm cliente = (ClienteForm) c;
                    return predicate.test(cliente);
                });
            } catch(SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    default Function<Dao, Optional<Long>> getIdOf(ClienteForm form) {
        return dao -> {
            try {
                Optional<Long> max =
                        dao.select(null).stream()
                                .filter( l -> ((ClienteForm)l).getDocumento().equals(form.getDocumento()))
                                .map( l -> ((ClienteForm) l).getId())
                                .findFirst();

                return max;
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
    };
}
