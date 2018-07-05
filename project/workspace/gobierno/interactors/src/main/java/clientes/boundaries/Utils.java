package clientes.boundaries;

import ar.edu.ubp.das.mvc.db.Dao;
import beans.ClienteForm;

import java.sql.SQLException;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Utils {

    default Function<Dao, Boolean> testClienteRegistrado(final ClienteForm form) {
        return dao -> {
            return exists(cl ->
                    cl.getDocumento().equals(form.getDocumento()) &&
                            cl.getConcesionariaId().equals(form.getConcesionariaId()))
                    .apply(dao);
        };
    }


    default Function<Dao, Boolean> exists(final Predicate<ClienteForm> predicate) {
        return dao -> {
            try {
                return dao.select(null).stream().anyMatch(c -> {
                    final ClienteForm cli = (ClienteForm) c;
                    return predicate.test(cli);
                });
            } catch (final SQLException e) {
                e.printStackTrace();
                return false;
            }
        };
    }
}
